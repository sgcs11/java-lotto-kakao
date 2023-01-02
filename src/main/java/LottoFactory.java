import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoFactory {

    private static final int LOTTO_NUMBER_COUNT = 6;

    private static final int LOTTO_PRICE = 1000;

    private static final LottoNumberPool lottoNumberPool = new LottoNumberPool();

    public static Lotto createLotto() {
        Collections.shuffle(lottoNumberPool.getLottoNumbers());
        return new Lotto(lottoNumberPool.getLottoNumbers().stream()
                .limit(LOTTO_NUMBER_COUNT)
                .collect(Collectors.toList()));
    }

    public static Lottos createLottos(int amount) {
        List<Lotto> lottoList = new ArrayList<>();
        for (int i = 0; i < amount / LOTTO_PRICE; i++) {
            lottoList.add(createLotto());
        }

        return new Lottos(lottoList);
    }


    public static int getLottoPrice() {
        return LOTTO_PRICE;
    }
}