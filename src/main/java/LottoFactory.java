import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoFactory {

    public static Lotto createLotto() {

        List<LottoNumber> lottoNumbers = LottoNumber.getLottoNumbers();

        Collections.shuffle(lottoNumbers);
        return new Lotto(lottoNumbers.stream()
                .limit(LottoInfo.LOTTO_NUMBER_COUNT.getValue())
                        .collect(Collectors.toList()));
    }

    public static Lottos createLottos(int amount) {
        List<Lotto> lottoList = new ArrayList<>();
        for (int i = 0; i < amount / LottoInfo.LOTTO_PRICE.getValue(); i++) {
            lottoList.add(createLotto());
        }

        return new Lottos(lottoList, LottoInfo.LOTTO_PRICE.getValue() * lottoList.size());
    }

    public static int getLottoPrice() {
        return LottoInfo.LOTTO_PRICE.getValue();
    }

}
