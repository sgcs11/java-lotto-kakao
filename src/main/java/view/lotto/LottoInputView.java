package view.lotto;

import java.util.Scanner;

public class LottoInputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static int inputAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        String input = scanner.nextLine();

        return Integer.parseInt(input);
    }

    public static String inputWinNumbers(){
        System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");

        return scanner.nextLine();
    }

    public static int inputBounsNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");

        return scanner.nextInt();
    }
}
