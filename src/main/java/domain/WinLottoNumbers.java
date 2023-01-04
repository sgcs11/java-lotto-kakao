package domain;

import java.util.List;

public class WinLottoNumbers {

    private final List<LottoNumber> lottoNumbers;

    private final LottoNumber bonusNumber;

    public WinLottoNumbers(final List<LottoNumber> lottoNumbers, final LottoNumber bonusNumber) {
        this.lottoNumbers = lottoNumbers;
        this.bonusNumber = bonusNumber;
    }

    public List<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }
}
