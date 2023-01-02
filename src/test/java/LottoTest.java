import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class LottoTest {

    @Test
    void 로또번호_6개를_발급한다() {
        List<Integer> LottoNumber = Lotto.createLotto();
        assertThat(LottoNumber).hasSize(6);
    }

    @Test
    void 로또번호는_1이상_45이하이다() {
        List<Integer> LottoNumber = Lotto.createLotto();
        for (int number : LottoNumber) {
            assertThat(number).isBetween(1, 45);
        }
    }

    @Test
    void 로또번호는_중복되지_않는다() {
        List<Integer> LottoNumber = Lotto.createLotto();
        assertThat(LottoNumber).doesNotHaveDuplicates();
    }

}
