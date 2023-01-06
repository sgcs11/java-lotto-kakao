package domain;

import java.util.Set;
import java.util.stream.Collectors;

public class Lotto{
    private final Set<LottoNumber> lottoNumbers;

    public Lotto (final Set<LottoNumber> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

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

    public Set<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }

    public int getMatchCount(final Set<LottoNumber> winNumbers) {
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
