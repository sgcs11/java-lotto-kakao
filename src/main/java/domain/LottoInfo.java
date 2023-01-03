package domain;

public enum LottoInfo {
    LOTTO_NUMBER_COUNT(6),
    LOTTO_PRICE(1000);

    private final int value;

    LottoInfo(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
