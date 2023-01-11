package domain;

public enum LottoCount {
    LOTTO_NORMAL_COUNT(6);

    private final int value;

    LottoCount(int value) {
        this.value = value;
    }

    public int get() {
        return value;
    }
}
