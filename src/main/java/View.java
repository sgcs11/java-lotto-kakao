import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class View {

    private static final Scanner scanner = new Scanner(System.in);
    public static int inputAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        int input = scanner.nextInt();
        return input;
    }
    public static String inputWinNumbers(){
        System.out.println("");
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        scanner.nextLine();
        String input = scanner.nextLine();
        return input;
    }

    public static int inputBounsNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        int input = scanner.nextInt();
        return input;
    }

    public static void printLottos(Lottos lottos) {
        System.out.println(String.format("%d개를 구매했습니다.", lottos.getCount()));
        lottos.getLottoList().forEach(lotto -> System.out.println(lotto.toString()));
    }

    public static void printResult(Lottos lottos, WinLottoNumbers winLottoNumbers) {
        Map<Integer, Integer> result = lottos.getTotalResult(winLottoNumbers);
        System.out.println("");
        System.out.println("당첨 통계");
        System.out.println("---------");
        System.out.println("3개 일치 (5000원)- " + result.getOrDefault(5, 0) +"개");
        System.out.println("4개 일치 (50000원)- " + result.getOrDefault(4, 0) + "개");
        System.out.println("5개 일치 (1500000원)- " + result.getOrDefault(3, 0) + "개");
        System.out.println("5개 일치, 보너스 볼 일치(30000000원) - " + result.getOrDefault(2, 0) + "개");
        System.out.println("6개 일치 (2000000000원)- " + result.getOrDefault(1, 0) +"개");
        double rate = lottos.getTotalLotteryRate(lottos.getTotalLotteryAmount(winLottoNumbers), lottos.getTotalPay());
        System.out.print(String.format("총 수익률은 %.2f 입니다.", rate));
    }
}
