package view.lotto;

import domain.Lottos;
import domain.WinLottoNumbers;

import java.util.Map;

public class LottoOutputView {
    public static void printLottos(Lottos manualIssueLottos, Lottos autoIssueLottos) {
        System.out.printf("수동으로 %d개, 자동으로 %d개를 구매했습니다.%n", manualIssueLottos.getLottoCount(), autoIssueLottos.getLottoCount());

        manualIssueLottos.getLottoList()
                .forEach(lotto -> System.out.println(lotto.toString()));
        autoIssueLottos.getLottoList()
                .forEach(lotto -> System.out.println(lotto.toString()));
    }

    public static void printResult(Lottos lottos, WinLottoNumbers winLottoNumbers) {
        Map<Integer, Integer> result = lottos.getTotalResult(winLottoNumbers);

        System.out.println("\n당첨 통계");
        System.out.println("---------");

        printEachWinCount(result);

        printEarnRate(lottos, winLottoNumbers);
    }

    private static void printEarnRate(final Lottos lottos, final WinLottoNumbers winLottoNumbers) {
        double rate = lottos.getTotalLotteryRate(lottos.getTotalLotteryAmount(winLottoNumbers), lottos.getTotalPay());

        System.out.printf("총 수익률은 %.2f 입니다.", rate);
    }

    private static void printEachWinCount(final Map<Integer, Integer> result) {
        System.out.println("3개 일치 (5,000원)- " + result.getOrDefault(5, 0) +"개");
        System.out.println("4개 일치 (50,000원)- " + result.getOrDefault(4, 0) + "개");
        System.out.println("5개 일치 (1,500,000원)- " + result.getOrDefault(3, 0) + "개");
        System.out.println("5개 일치, 보너스 볼 일치(30,000,000원) - " + result.getOrDefault(2, 0) + "개");
        System.out.println("6개 일치 (2,000,000,000원)- " + result.getOrDefault(1, 0) +"개");
    }
}
