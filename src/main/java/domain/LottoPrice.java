package domain;

public enum LottoPrice {
    LOTTO_NORMAL_PRICE(1000);

    private final int value;

    LottoPrice(int value) {
        this.value = value;
    }

    public int get() {
        return value;
    }
}
