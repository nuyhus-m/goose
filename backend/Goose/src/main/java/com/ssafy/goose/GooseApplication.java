package com.ssafy.goose;

import com.ssafy.goose.domain.news.scheduler.AutoCrawlingService;
import com.ssafy.goose.domain.factcheck.scheduler.AutoFactCheckCrawlingService;
import com.ssafy.goose.domain.news.service.bias.BiasAnalyseService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling // 🔹 @Scheduled 활성화
public class GooseApplication {

	public static void main(String[] args) {
		SpringApplication.run(GooseApplication.class, args);
	}

	// 🔹 앱 시작 후 즉시 뉴스 크롤링 실행 및 제목 검증 테스트
	@Bean
	public CommandLineRunner run(
			AutoCrawlingService autoCrawlingService,
			AutoFactCheckCrawlingService autoFactCheckCrawlingService,
			BiasAnalyseService biasAnalyseService
	) {
		return args -> {
			// ✅ 자동 뉴스 크롤링 (비활성화 가능)
//			 System.out.println("🚀 애플리케이션 실행 후 즉시 뉴스 크롤링 시작...");
//			 autoCrawlingService.fetchAndSaveTrendingNews();
//			 autoFactCheckCrawlingService.fetchAndStoreFactChecks();

			// ✅ 제목 검증 테스트 실행
//			String testTitle = "트럼프, 철강에 관세 25% 추가…포스코·현대제철 미 공장 검토";  // 테스트할 기사 제목
//			String testContent = """
//					도널드 트럼프 미국 대통령이 모든 수입 철강에 관세 25% 부과 방침을 10일(현지시간) 발표하겠다고 예고하면서 국내 철강업계에 비상이 걸렸다. 한국 철강 기업들의 미국 수출품에 관세가 추가되면 현지에서 경쟁하는 미국·일본 철강 대비 가격 경쟁력이 떨어질 수밖에 없다. 트럼프 대통령의 관세 정책에 한국이 첫 직격탄을 맞는 것이다. 기업들은 미국 현지 생산과 생산시설 투자를 늘리는 방안 등 대책 마련에 나섰다.
//					트럼프 대통령은 9일 미국프로풋볼(NFL) 챔피언결정전 ‘수퍼보울’이 열리는 뉴올리언스로 향하는 전용기 에어포스원에서 가진 기자간담회에서 이 같은 계획을공개했다. 그는 “알루미늄에도 똑같이 적용할 것”이라고 덧붙였다.
//					앞서 트럼프 대통령은 2018년 1기 행정부에서도 무역확장법 232조를 적용, 수입산 철강에 25% 보편관세를 부과했다. 미국 철강산업이 외국 기업들에 잠식돼 국가 안보에 위협이 된다는 이유에서다. 당시 한국은 협상을 통해 2015~2017년 연평균 수출량(약 383만t)의 70%인 263만t까지만 수출하되 무관세를 적용받기로 합의했고, 일본·영국·유럽연합(EU) 등은 쿼터까지는 무관세를, 그 이상 수출분에는 25% 관세를 부과받았다. 결과적으로 쿼터제 이후, 한국의 연간 대미 철강 수출량은 100만t가량 줄었다.
//					미국의 철강 시장은 연간 약 1억t 규모다. 이 중 8000만t이 현지 생산, 나머지 20%는 수입한다. 그런데도 트럼프 정부가 1기 때에 이어 다시 관세 카드를 꺼낸 건 미국산 철강 제품 보호 목적이 크다. 미국 철강 기업들은 인건비 부담이 크고, 기존 화석연료 고로보다 탄소 배출이 적은 전기로(電氣爐) 전환도 늦어, 수입품 대비 생산 단가가 높은 편이다.
//					━
//					‘263만t 무관세’ 한국 철강, 쿼터 폐지 땐 가격경쟁력 타격
//					이승훈 연세대 경제학과 교수는 “이번 관세 부과 발언은 미국 철강 기업들의 가격 경쟁력을 지켜주고, 해외 기업들로부터 현지 투자를 유치하기 위한 전략”이라고 말했다. US스틸 인수를 시도하던 일본제철이 지난 7일 이시바 시게루 총리와 트럼프 대통령의 정상회담 이후 ‘인수 대신 투자’ 형태로 프레임을 바꾼 것도 자국 산업 보호에 방점을 둔 트럼프 정부 기조에 맞춘 것이란 해석이 나온다.
//					국내 철강업계는 현지 투자 확대 방안을 검토하고 있다. 현대차 계열사인 현대제철은 10조원가량을 투자해 미국에 첫 제철소를 지을 예정이다. 현재 미국 남부 루이지애나주가 유력 후보지로 검토되고 있다. 포스코 역시 미국 현지 생산 방안을 검토 중인 것으로 알려졌다. 현지 합작 법인을 설립하거나 현지 제철소 인수 등 다양한 방안이 거론된다. 세아그룹은 텍사스주에 연간 6000만t 생산 규모의 특수합금 공장 건설을 추진 중인데, 가속도가 붙을 것으로 보고 있다.
//					한 철강 기업 관계자는 “철강 관세 25%의 적용 대상이나 방식 등이 아직 구체적으로 공개되지 않았기 때문에, 현지 네트워크 등을 총동원해 상황을 파악하는 중”이라고 말했다. 최악의 시나리오는 트럼프 행정부가 모든 국가를 대상으로 무관세 쿼터제를 철폐하거나 축소하는 것이다. 알루미늄 업계도 긴장하긴 마찬가지다. 알루미늄박의 대미 수출 비중은 전체 8만t 중 약 38%다.
//					정부는 이날 최상목 대통령 권한대행 주재로 외교·통상 장관들이 참석한 ‘대외 경제현안 간담회’를 열고 미국의 철강·알루미늄 관세 부과 발표에 대한 대응 방안을 점검했다. 아울러 산업통상자원부는 박종원 통상차관보 주재로 긴급 점검 회의를 개최했다. 산업부는 “우리 기업에 미치는 영향이 최소화되도록 적극적으로 대응하겠다”고 강조했다.
//					일각에서는 조선·자동차 산업에 원자재를 대는 후방 산업인 철강이 미국에서 새로운 기회를 찾을 수 있다고도 본다. 트럼프 행정부가 현지의 선박 건조 및 방산 시장을 키울 경우 현지에서 생산된 한국 기업들의 철강 제품 수요가 늘 수 있다는 기대다. 채우석 한국방위산업학회장은 “미국 제조업 중 특히 방산·조선 산업은 우방국의 제품을 사용할 수밖에 없을 것”이라고 말했다.
//					문제는 국내 일자리다. 미국 생산량 확대 시 국내 공장 가동 중단, 인력 조정 등 구조조정이 불가피해진다. 현대제철은 지난해 3곳을, 포스코도 이미 2곳을 중단했다. 한 철강업계 관계자는 “달라진 통상 환경에서 해외 생산 확대는 불가피한 선택인 만큼 국내 인력 조정을 최소화하는 방안에서 신중하게 검토할 예정”이라고 말했다.
//					""";  // 테스트할 기사 제목
//
//			System.out.println("📝 Bias 검증 테스트 시작: ");
//
//			double biasScore = biasAnalyseService.analyzeBias(testTitle, testContent);
//			System.out.println("🔍 편향성 검증 결과: " + biasScore);
		};
	}
}
