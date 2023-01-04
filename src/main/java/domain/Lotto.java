package domain;

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

    public int getLotteryAmount(final int rank) {
        return LotteryAmount.values()[rank - 1].get();
    }

    public int getLotteryRank(final int matchCount, boolean isBonusMatch) {
        switch(matchCount) {
            case 6: return 1;
            case 5: return (isBonusMatch)? 2:3;
            case 4: return 4;
            case 3: return 5;
            default: return 6;
        }
    }

    public List<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }

    public int getMatchCount(final List<LottoNumber> winNumbers) {
        return (int)lottoNumbers.stream()
                        .filter(winNumbers::contains)
                        .count();
    }

    public boolean isMatchBonusNumber(LottoNumber bonusNumber) {
        return lottoNumbers.contains(bonusNumber);
    }

    @Override
    public String toString() {
        return "["  + lottoNumbers.stream()
                        .map(LottoNumber::getNumber)
                        .map(String::valueOf)
                        .collect(Collectors.joining(",")) + "]";
    }
}
