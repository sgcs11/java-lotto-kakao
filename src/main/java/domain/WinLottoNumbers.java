package domain;

import java.util.List;

public class WinLottoNumbers {

    private final List<LottoNumber> lottoNumbers;

    private LottoNumber bonusNumber;

    public WinLottoNumbers(final List<LottoNumber> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public List<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }

    public void setBonusNumber(final LottoNumber bonusNumber) {
        this.bonusNumber = bonusNumber;
    }
}
