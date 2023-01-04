package controller;

import domain.LottoFactory;
import domain.LottoNumber;
import domain.Lottos;
import domain.WinLottoNumbers;
import util.Parser;
import view.lotto.LottoInputView;
import view.lotto.LottoOutputView;

import java.util.List;

public class LottoController {

    public static void run() {
        Lottos lottos = LottoFactory.createLottos(LottoInputView.inputAmount());
        LottoOutputView.printLottos(lottos);

        List<LottoNumber> lottoNumbers = Parser.parsingWinNumbers(LottoInputView.inputWinNumbers());
        LottoNumber bonusLottoNumber = LottoNumber.getLottoNumber(LottoInputView.inputBounsNumber());
        WinLottoNumbers winLottoNumbers = new WinLottoNumbers(lottoNumbers, bonusLottoNumber);

        LottoOutputView.printResult(lottos, winLottoNumbers);
    }
}
