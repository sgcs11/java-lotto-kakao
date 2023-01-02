/**
 * 로또넘버 자료형의 리스트 하나만 가지고 있음 -> 일급컬렉션...??
 */

import java.util.List;

public class Lotto {

    private final List<LottoNumber> lottoNumbers;

    public Lotto (List<LottoNumber> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public List<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }
}
