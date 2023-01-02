//1~45

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class LottoNumber {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    private static final List<LottoNumber> lottoNumbers = new ArrayList<>();

    private int number;

    static {
        IntStream.range(MIN_NUMBER, MAX_NUMBER)
                .forEach(number -> lottoNumbers.add(new LottoNumber(number)));
    }

    private LottoNumber(int number) {
        this.number = number;
    }

    public static List<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }

    public int getLottoNumber() {
        return number;
    }
}

