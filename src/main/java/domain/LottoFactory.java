package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoFactory {

    public static Lotto CreateAutoLotto() {
        List<LottoNumber> lottoNumbers = LottoNumber.getLottoNumbers();

        Collections.shuffle(lottoNumbers);
        return new Lotto(lottoNumbers.stream()
                .limit(LottoCount.LOTTO_NORMAL_COUNT.get())
                .collect(Collectors.toSet()));
    }

    public static Lotto createManualLotto(Set<LottoNumber> lottoNumbers) {
        return new Lotto(lottoNumbers);
    }

    public static Lottos createLottos(Lottos autoCreateLottos, Lottos manualCreateLottos) {
        List<Lotto> lottoList = new ArrayList<>();

        lottoList.addAll(manualCreateLottos.getLottoList());
        lottoList.addAll(autoCreateLottos.getLottoList());

        return new Lottos(lottoList);
    }

    public static Lottos createAutoLottos(int autoCreateAmount) {
        List<Lotto> lottoList = new ArrayList<>();

        for (int i = 0; i < autoCreateAmount / LottoPrice.LOTTO_NORMAL_PRICE.get(); i++) {
            lottoList.add(CreateAutoLotto());
        }

        return new Lottos(lottoList);
    }

    public static Lottos createManualLottos(List<Lotto> manualLottos) {
        return new Lottos(manualLottos);
    }
}
