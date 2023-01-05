package domain;

import java.util.List;
import java.util.Set;

public class WinLottoNumbers {

    private final Set<LottoNumber> lottoNumbers;

    private final LottoNumber bonusNumber;

    public WinLottoNumbers(final Set<LottoNumber> lottoNumbers, final LottoNumber bonusNumber) {
        this.lottoNumbers = lottoNumbers;
        this.bonusNumber = bonusNumber;
    }

    public Set<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }
}
