import java.util.List;
import java.util.stream.Collectors;

public class Lotto{

    private final List<LottoNumber> lottoNumbers;

    public Lotto (final List<LottoNumber> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    /**
     * 3개 일치 (5000원)- 1개
     * 4개 일치 (50000원)- 0개
     * 5개 일치 (1500000원)- 0개
     * 5개 일치, 보너스 볼 일치(30000000원) - 0개
     * 6개 일치 (2000000000원)- 0개
     */

    public static int getLotteryAmount(final int matchCount, boolean isBonusMatch) {
        if (matchCount == 6) return 2000000000;
        if (matchCount == 5 && isBonusMatch) return 30000000;
        if (matchCount == 5) return 1500000;
        if (matchCount == 4) return 50000;
        if (matchCount == 3) return 5000;

        return 0;
    }

    public static int getLotteryResult(final int matchCount, boolean isBonusMatch) {
        if (matchCount == 6) return 1;
        if (matchCount == 5 && isBonusMatch) return 2;
        if (matchCount == 5) return 3;
        if (matchCount == 4) return 4;
        if (matchCount == 3) return 5;
        return 0;
    }

    public List<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }

    public long getMatchCount(final List<LottoNumber> winNumbers) {
        long count = lottoNumbers.stream()
                        .filter(lottoNumber -> winNumbers.contains(lottoNumber))
                        .count();

        return count;
    }

    public boolean isMatchBonusNumber(LottoNumber bonusNumber) {
        return lottoNumbers.contains(bonusNumber);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        stringBuilder.append(lottoNumbers.stream().map(LottoNumber::getNumber).map(String::valueOf).collect(Collectors.joining(",")));
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
