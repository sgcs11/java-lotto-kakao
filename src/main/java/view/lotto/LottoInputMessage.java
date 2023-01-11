package view.lotto;

public enum LottoInputMessage {
    INPUT_AMOUNT_MESSAGE("구입금액을 입력해 주세요."),

    INPUT_WIN_NUMBERS_MESSAGE("\n지난 주 당첨 번호를 입력해 주세요."),

    INPUT_BONUS_NUMBER("보너스 볼을 입력해 주세요."),

    INPUT_MANUAL_COUNT_MESSAGE("\n수동으로 구매할 로또 수를 입력해 주세요."),

    INPUT_MANUAL_LOTTO_NUMBERS_MESSAGE("\n수동으로 구매할 번호를 입력해 주세요.");

    private final String message;

    LottoInputMessage(String message) {
        this.message = message;
    }

    public String get() {
        return message;
    }

}
