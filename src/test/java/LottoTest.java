import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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
        for (LottoNumber number : lotto.getLottoNumbers()) {
            assertThat(number.getNumber()).isBetween(1, 45);
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
}
