package util;

import domain.Lotto;
import domain.LottoNumber;
import domain.WinLottoNumbers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Parser {

    public static List<LottoNumber> parsingWinNumbers(final String input) {
        String[] inputs = input.replace(" ", "").split(",");

        return Arrays.stream(inputs)
                .map(Integer::parseInt)
                .map(LottoNumber::getLottoNumber)
                .collect(Collectors.toList());
    }
}
