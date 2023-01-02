import java.util.List;

public class Lotto{

    private final List<LottoNumber> lottoNumbers;

    public Lotto (final List<LottoNumber> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
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

}
