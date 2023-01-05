package domain;

import domain.util.validation.LottoValidator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoFactory {

    public static Lotto CreateAutoLotto() {
        List<LottoNumber> lottoNumbers = LottoNumber.getLottoNumbers();

        Collections.shuffle(lottoNumbers);

        Lotto lotto = new Lotto(lottoNumbers.stream()
                .limit(LottoInfo.LOTTO_NUMBER_COUNT.getValue())
                .collect(Collectors.toSet()));
        LottoValidator.validate(lotto);
        return lotto;
    }

    public static Lotto createManualLotto(Set<LottoNumber> lottoNumbers) {
        Lotto lotto = new Lotto(lottoNumbers);
        LottoValidator.validate(lotto);
        return lotto;
    }

    public static Lottos createLottos(Lottos autoCreateLottos, Lottos manualCreateLottos) {
        List<Lotto> lottoList = new ArrayList<>();

        lottoList.addAll(manualCreateLottos.getLottoList());
        lottoList.addAll(autoCreateLottos.getLottoList());

        return new Lottos(lottoList);
    }

    public static Lottos createAutoLottos(int autoCreateAmount) {
        List<Lotto> lottoList = new ArrayList<>();

        for (int i = 0; i < autoCreateAmount / LottoInfo.LOTTO_PRICE.getValue(); i++) {
            lottoList.add(CreateAutoLotto());
        }

        return new Lottos(lottoList);
    }

    public static Lottos createManualLottos(List<Lotto> manualLottos) {
        return new Lottos(manualLottos);
    }
}
