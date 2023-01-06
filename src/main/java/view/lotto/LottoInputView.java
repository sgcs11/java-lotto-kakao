package view.lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LottoInputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static String inputAmount() {
        System.out.println(LottoInputMessage.INPUT_AMOUNT_MESSAGE.get());
        return scanner.nextLine();
    }

    public static String inputWinNumbers(){
        System.out.println(LottoInputMessage.INPUT_WIN_NUMBERS_MESSAGE.get());
        return scanner.nextLine();
    }

    public static String inputBonusNumber() {
        System.out.println(LottoInputMessage.INPUT_BONUS_NUMBER.get());
        return scanner.nextLine();
    }

    public static String inputManualCount() {
        System.out.println(LottoInputMessage.INPUT_MANUAL_COUNT_MESSAGE.get());
        return scanner.nextLine();
    }

    public static List<String> inputManualLottoNumbers(int manualIssueCount) {
        List<String> inputs = new ArrayList<>(manualIssueCount);
        System.out.println(LottoInputMessage.INPUT_MANUAL_LOTTO_NUMBERS_MESSAGE.get());

        for (int i = 0; i < manualIssueCount; i ++) {
            inputs.add(scanner.nextLine());
        }
        return inputs;
    }
}
