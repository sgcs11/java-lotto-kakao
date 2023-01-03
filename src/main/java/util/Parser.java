package util;

import domain.LottoNumber;
import domain.WinLottoNumbers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Parser {

    public static WinLottoNumbers parsingWinNumbers(final String input) {
        String[] inputs = input.replace(" ", "").split(",");

        List<LottoNumber> lottoNumberList = Arrays.stream(inputs)
                .map(Integer::parseInt)
                .map(LottoNumber::getLottoNumber)
                .collect(Collectors.toList());

        return new WinLottoNumbers(lottoNumberList);
    }
}
