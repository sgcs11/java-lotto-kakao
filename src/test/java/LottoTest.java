import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class LottoTest {

    @Test
    void 로또번호_6개를_발급한다() {
        Lotto lotto = LottoFactory.createLotto();
        assertThat(lotto.getLottoNumbers()).hasSize(6);
    }

    @Test
    void 로또번호는_1이상_45이하이다() {
        Lotto lotto = LottoFactory.createLotto();
        for (LottoNumber lottoNumber : lotto.getLottoNumbers()) {
            assertThat(lottoNumber.getLottoNumber()).isBetween(1, 45);
        }
    }

    @Test
    void 로또번호는_중복되지_않는다() {
        Lotto lotto = LottoFactory.createLotto();
        assertThat(lotto.getLottoNumbers()).doesNotHaveDuplicates();
    }

    @ParameterizedTest
    @ValueSource(ints = {10000, 20000, 5000, 1000, 3000})
    void  로또_구입_금액에_해당하는_로또를_발급한다(final int amount) {
        Lottos lottos = LottoFactory.createLottos(amount);
        assertThat(lottos.getLottoList()).hasSize(amount / LottoFactory.getLottoPrice());
    }

    //@ParameterizedTest
    //@ValueSource(ints = {10000, 20000, 5000, 1000, 3000})
    @Test
    void 로또_번호가_몇_개_일치하는지_당첨_통계를_계산하여_출력한다() {
        List<LottoNumber> lottonumbers = new ArrayList<>();
        for(int i = 1; i < 7; i++) lottonumbers.add(LottoNumber.getLottoNumbers().get(i));
        Lotto lotto = new Lotto(lottonumbers);

        List<LottoNumber> lottonumbers2 = new ArrayList<>();
        for(int i = 1; i < 7; i++) lottonumbers2.add(LottoNumber.getLottoNumbers().get(i*2));

        assertThat(lotto.getMatchCount(lottonumbers)).isEqualTo(6);
        assertThat(lotto.getMatchCount(lottonumbers2)).isEqualTo(3);
    }
}
