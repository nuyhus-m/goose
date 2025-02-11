from fastapi import FastAPI, HTTPException
from pydantic import BaseModel
import torch
from transformers import (
    BartForConditionalGeneration, PreTrainedTokenizerFast,
    AutoModelForSequenceClassification, AutoTokenizer
)

app = FastAPI()

# ✅ GPU 또는 CPU 자동 감지
device = torch.device("cuda" if torch.cuda.is_available() else "cpu")

# ✅ 1. KoBART 모델 로드 (텍스트 요약)
kobart_model = BartForConditionalGeneration.from_pretrained("digit82/kobart-summarization").to(device)
kobart_tokenizer = PreTrainedTokenizerFast.from_pretrained("digit82/kobart-summarization")

# ✅ 2. KLUE-RoBERTa Large 모델 로드 (자연어 추론)
nli_model = AutoModelForSequenceClassification.from_pretrained("klue/roberta-large").to(device)
nli_tokenizer = AutoTokenizer.from_pretrained("klue/roberta-large")

class FactCheckRequest(BaseModel):
    title: str
    content: str

@app.post("/title-compare-contents")
async def fact_check(request: FactCheckRequest):
    try:
        title = request.title
        content = request.content

        # ✅ 1. 뉴스 본문 요약 (KoBART)
        inputs = kobart_tokenizer.encode("summarize: " + content, return_tensors="pt", max_length=300, truncation=True).to(device)
        with torch.no_grad():
            summary_ids = kobart_model.generate(inputs, max_length=300, num_beams=4, early_stopping=True)
        summary = kobart_tokenizer.decode(summary_ids[0], skip_special_tokens=True)

        print("🔹 [메인 뉴스 타이틀] :", title)
        print("🔹 [요약된 내용] :", summary)

        # ✅ 2. 자연어 추론 (팩트 검증)
        premise = summary  # 요약된 본문 (전제)
        hypothesis = title  # 팩트체크 제목 (가설)

        # ✅ 3. 입력 데이터 변환 및 예측 (메모리 최적화)
        with torch.no_grad():
            encoded_input = nli_tokenizer(premise, hypothesis, return_tensors="pt", truncation=True, padding=True).to(device)
            output = nli_model(**encoded_input)
            probs = torch.nn.functional.softmax(output.logits, dim=1)

        # ✅ 4. 확률값 기반 판별 로직 개선
        num_classes = probs.shape[1]  # 모델의 실제 출력 크기
        entailment_prob = probs[0][0].item()  # True 확률
        neutral_prob = probs[0][1].item() if num_classes == 3 else 0.5  # 2-class 모델 대비
        contradiction_prob = probs[0][2].item() if num_classes == 3 else 1 - entailment_prob  # 2-class 모델 대비

        if contradiction_prob > 0.6:
            result = "False"
        elif entailment_prob > 0.6:
            result = "True"
        elif neutral_prob > 0.6:
            result = "Neutral"
        else:
            result = "Partially True"

        print(f"🔹 [검증 결과] : {result} (Entailment: {entailment_prob:.2f}, Contradiction: {contradiction_prob:.2f})")

        return {"title": title, "summary": summary, "factcheck_result": result}

    except Exception as e:
        raise HTTPException(status_code=500, detail=str(e))
