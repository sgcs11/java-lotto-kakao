package controller;

import domain.*;
import domain.util.validation.LottoValidator;
import util.Parser;
import view.lotto.LottoInputView;
import view.lotto.LottoOutputView;

import java.util.Set;
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
        Set<String> inputManualLottoNumbers = LottoInputView.inputManualLottoNumbers(LottoInputView.inputManualCount());

        return LottoFactory.createManualLottos(inputManualLottoNumbers
                .stream()
                .map(Parser::parsingLottoNumbers)
                .map(LottoFactory::createManualLotto)
                .collect(Collectors.toList()));
    }

    private static void resultProcess(final Lottos lottos) {
        Set<LottoNumber> lottoNumbers = Parser.parsingLottoNumbers(LottoInputView.inputWinNumbers());

        LottoNumber bonusLottoNumber = LottoNumber.getLottoNumber(LottoInputView.inputBounsNumber());
        WinLottoNumbers winLottoNumbers = new WinLottoNumbers(lottoNumbers, bonusLottoNumber);
        LottoValidator.validate(winLottoNumbers);

        LottoOutputView.printResult(lottos, winLottoNumbers);
    }
}
