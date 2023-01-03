package controller;

import domain.LottoFactory;
import domain.LottoNumber;
import domain.Lottos;
import domain.WinLottoNumbers;
import util.Parser;
import view.View;

import java.util.List;

public class LottoController {

    public static void run() {
        Lottos lottos = LottoFactory.createLottos(View.inputAmount());
        View.printLottos(lottos);

        List<LottoNumber> lottoNumbers = Parser.parsingWinNumbers(View.inputWinNumbers());
        LottoNumber bonusLottoNumber = LottoNumber.getLottoNumber(View.inputBounsNumber());
        WinLottoNumbers winLottoNumbers = new WinLottoNumbers(lottoNumbers, bonusLottoNumber);

        View.printResult(lottos, winLottoNumbers);
    }
}
