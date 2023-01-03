import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Parser {

    public static WinLottoNumbers parsingWinNumbers(final String input) {
        List<LottoNumber> lottoNumberList = Arrays.stream(input.replace(" ", "")
                        .split(","))
                .map(Integer::parseInt)
                .map(LottoNumber::getLottoNumber)
                .collect(Collectors.toList());
        WinLottoNumbers winLottoNumbers = new WinLottoNumbers();
        winLottoNumbers.setWinNumbers(lottoNumberList);

        return winLottoNumbers;
    }
}
