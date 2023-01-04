package controller;

import domain.*;
import util.Parser;
import view.lotto.LottoInputView;
import view.lotto.LottoOutputView;

import java.util.List;
import java.util.stream.Collectors;

public class LottoController {

    public static void run() {
        int totalIssueCost = LottoInputView.inputAmount();

        Lottos manualIssueLottos = manualIssueProcess();
        Lottos autoIssueLottos = autoIssueProcess(totalIssueCost, LottoInfo.LOTTO_PRICE.getValue() * manualIssueLottos.getLottoList().size());
        LottoOutputView.printLottos(manualIssueLottos, autoIssueLottos);

        Lottos lottos = LottoFactory.createLottos(autoIssueLottos, manualIssueLottos);
        resultProcess(lottos);
    }

    private static Lottos autoIssueProcess(int totalIssueCost, int manualIssueCost) {
        return LottoFactory.createAutoLottos(totalIssueCost - manualIssueCost);
    }

    private static Lottos manualIssueProcess() {
        List<String> inputManualLottoNumbers = LottoInputView.inputManualLottoNumbers(LottoInputView.inputManualCount());

        return LottoFactory.createManualLottos(inputManualLottoNumbers
                .stream()
                .map(Parser::parsingLottoNumbers)
                .map(LottoFactory::createManualLotto)
                .collect(Collectors.toList()));
    }

    private static void resultProcess(final Lottos lottos) {
        List<LottoNumber> lottoNumbers = Parser.parsingLottoNumbers(LottoInputView.inputWinNumbers());

        LottoNumber bonusLottoNumber = LottoNumber.getLottoNumber(LottoInputView.inputBounsNumber());
        WinLottoNumbers winLottoNumbers = new WinLottoNumbers(lottoNumbers, bonusLottoNumber);

        LottoOutputView.printResult(lottos, winLottoNumbers);
    }
}
