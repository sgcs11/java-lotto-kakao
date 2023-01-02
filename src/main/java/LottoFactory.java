import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoFactory {

    private static final int LOTTO_NUMBER_COUNT = 6;

    private static final List<LottoNumber> lottoNumbers;

    static {
        lottoNumbers = new ArrayList<>();
        IntStream.range(1, 45)
                .forEach(number -> lottoNumbers.add(new LottoNumber(number)));
    }

    public static Lotto createLotto() {
        Collections.shuffle(lottoNumbers);
        return new Lotto(lottoNumbers.stream()
                .limit(LOTTO_NUMBER_COUNT)
                .collect(Collectors.toList()));
    }


}
