package domain;

public enum LotteryAmount {
    FIRST(2_000_000_000),
    SECOND(30_000_000),
    THIRD(1_500_000),
    FOURTH(50_000),
    FIFTH(5_000),
    OTHER(0);

    private final int value;

    LotteryAmount(int value) {
        this.value = value;
    }

    public int get() {
        return value;
    }
}
