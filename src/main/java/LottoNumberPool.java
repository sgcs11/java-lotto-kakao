//1~45

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class LottoNumberPool {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    private static final List<LottoNumber> lottoNumbers = new ArrayList<>();

    static {
        IntStream.range(MIN_NUMBER, MAX_NUMBER)
                .forEach(number -> lottoNumbers.add(new LottoNumber(number)));
    }

    public List<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }

    public LottoNumber getLottoNumber(int number) {
        return lottoNumbers.get(number-1);
    }
}

