import java.util.ArrayList;
import java.util.List;

public class WinLottoNumbers {

    private List<LottoNumber> numbers = new ArrayList<>();
    private LottoNumber bonusNumber;

    public List<LottoNumber> getNumbers() {
        return numbers;
    }

    public void setWinNumbers(final List<LottoNumber> lottoNumbers) {
        this.numbers = lottoNumbers;
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }

    public void setBonusNumber(final LottoNumber bonusNumber) {
        this.bonusNumber = bonusNumber;
    }
}
