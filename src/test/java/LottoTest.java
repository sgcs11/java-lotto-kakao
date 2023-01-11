import domain.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTest {

    private static Lottos lottos;

    @BeforeAll
    static void makeLottos() {
        List<Lotto> lottoList = new ArrayList<>();

        int[][] inputs= {
                {8, 21, 23, 41, 42, 43},
                {3, 5, 11, 16, 32, 38},
                {7, 11, 16, 35, 36, 44},
                {1, 8, 11, 31, 41, 42},
                {13, 14, 16, 38, 42, 45},
                {7, 11, 30, 40, 42, 43},
                {2, 13, 22, 32, 38, 45},
                {23, 25, 33, 36, 39, 41},
                {1, 3, 5, 14, 22, 45},
                {5, 9, 38, 41, 43, 44},
                {2, 8, 9, 18, 19, 21},
                {13, 14, 18, 21, 23, 35},
                {17, 21, 29, 37, 42, 45},
                {3, 8, 27, 30, 35, 44},
                {1, 2, 3, 4, 5, 7}
        };

        for(int[] input : inputs) {
            List<Integer> tmp = Arrays.stream(input)
                    .boxed()
                    .collect(Collectors.toList());
            lottoList.add(new Lotto(asLottoNumbers(tmp)));
        }
        lottos = new Lottos(lottoList);
    }

    @Test
    void 로또번호_6개를_발급한다() {
        Lotto lotto = LottoFactory.CreateAutoLotto();
        assertThat(lotto.getLottoNumbers()).hasSize(6);
    }

    @Test
    void 로또번호는_1이상_45이하이다() {
        Lotto lotto = LottoFactory.CreateAutoLotto();
        for (LottoNumber lottoNumber : lotto.getLottoNumbers()) {
            assertThat(lottoNumber.getNumber()).isBetween(1, 45);
        }
    }

    @Test
    void 로또번호는_중복되지_않는다() {
        Lotto lotto = LottoFactory.CreateAutoLotto();
        assertThat(lotto.getLottoNumbers()).doesNotHaveDuplicates();
    }

    @ParameterizedTest
    @ValueSource(ints = {10_000, 20_000, 5_000, 1_000, 3_000})
    void  로또_구입_금액에_해당하는_로또를_발급한다(final int amount) {
        Lottos lottos = LottoFactory.createAutoLottos(amount);
        assertThat(lottos.getLottoList()).hasSize(amount / LottoPrice.LOTTO_NORMAL_PRICE.get());
    }

    static Set<LottoNumber> asLottoNumbers (List<Integer> lottoNumbers) {
        return lottoNumbers.stream()
                .map(LottoNumber::getLottoNumber)
                .collect(Collectors.toSet());
    }

    static Stream<Arguments> lottoData() {
        return Stream.of(
                Arguments.of(asLottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6)), asLottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6)), 6),
                Arguments.of(asLottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6)), asLottoNumbers(Arrays.asList(2, 4, 6, 8, 10, 20)), 3)
        );
    }

    @ParameterizedTest
    @MethodSource("lottoData")
    void 로또_번호가_몇_개_일치하는지_계산한다(Set<LottoNumber> lottoNumbers, Set<LottoNumber> winNumbers, int answer) {
        Lotto lotto = new Lotto(lottoNumbers);
        assertThat(lotto.getMatchCount(winNumbers)).isEqualTo(answer);
    }

    static Stream<Arguments> lottoData2() {
        return Stream.of(
                Arguments.of(asLottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6)), asLottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6)), 2_000_000_000),
                Arguments.of(asLottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6)), asLottoNumbers(Arrays.asList(2, 4, 6, 8, 10, 20)), 5_000)
        );
    }

    @ParameterizedTest
    @MethodSource("lottoData2")
    void 로또의_당첨금액을_계산한다(Set<LottoNumber> lottoNumbers, Set<LottoNumber> winNumbers, int answer) {
        Lotto lotto = new Lotto(lottoNumbers);
        int matchCount = lotto.getMatchCount(winNumbers);
        boolean isBonusMatch = false;
        int rank = lotto.getLotteryRank(matchCount, isBonusMatch);

        assertThat(lotto.getLotteryAmount(rank)).isEqualTo(answer);
    }

    @Test
    void 총_수익률을_계산하여_출력한다() {
        Set<LottoNumber> winNumbers = asLottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6));
        WinLottoNumbers winLottoNumbers = new WinLottoNumbers(winNumbers, LottoNumber.getLottoNumber(7));

        assertThat(Math.floor(lottos.getTotalLotteryRate(lottos.getTotalLotteryAmount(winLottoNumbers), 15_000) * 100)).isEqualTo(200_033);
    }

    @Test
    void 로또를_수동으로_발급한다() {
        Lottos manualLottos = LottoFactory.createManualLottos(lottos.getLottoList());
        assertThat(manualLottos.getLottoList()).hasSize(lottos.getLottoCount());
    }
}