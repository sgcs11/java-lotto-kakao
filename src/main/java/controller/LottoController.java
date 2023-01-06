package controller;

import domain.*;
import domain.util.validation.LottoValidator;
import util.Parser;
import view.lotto.LottoInputView;
import view.lotto.LottoOutputView;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoController {

    public static void run() {
        String totalIssueCost = LottoInputView.inputAmount();
        LottoValidator.isValidAmount(totalIssueCost);

        Lottos manualIssueLottos = manualIssueProcess();
        Lottos autoIssueLottos = autoIssueProcess(Integer.parseInt(totalIssueCost), LottoInfo.LOTTO_PRICE.getValue() * manualIssueLottos.getLottoList().size());
        LottoOutputView.printLottos(manualIssueLottos, autoIssueLottos);

        Lottos lottos = LottoFactory.createLottos(autoIssueLottos, manualIssueLottos);
        resultProcess(lottos);
    }

    private static Lottos autoIssueProcess(int totalIssueCost, int manualIssueCost) {
        return LottoFactory.createAutoLottos(totalIssueCost - manualIssueCost);
    }

    private static Lottos manualIssueProcess() {
        String manualCount = LottoInputView.inputManualCount();
        LottoValidator.isValidManualCount(manualCount);
        List<String> inputManualLottoNumbers = LottoInputView.inputManualLottoNumbers(Integer.parseInt(manualCount));
        inputManualLottoNumbers.forEach(LottoValidator::isValidSixNumbers);

        return LottoFactory.createManualLottos(inputManualLottoNumbers
                .stream()
                .map(Parser::parsingLottoNumbers)
                .map(LottoFactory::createManualLotto)
                .collect(Collectors.toList()));
    }

    private static void resultProcess(final Lottos lottos) {
        String inputWinNumbers = LottoInputView.inputWinNumbers();
        LottoValidator.isValidSixNumbers(inputWinNumbers);
        Set<LottoNumber> lottoNumbers = Parser.parsingLottoNumbers(inputWinNumbers);

        String inputBonusNumber = LottoInputView.inputBonusNumber();
        LottoValidator.isValidLottoNumber(inputBonusNumber);

        LottoNumber bonusLottoNumber = LottoNumber.getLottoNumber(Integer.parseInt(inputBonusNumber));
        WinLottoNumbers winLottoNumbers = new WinLottoNumbers(lottoNumbers, bonusLottoNumber);

        LottoOutputView.printResult(lottos, winLottoNumbers);
    }
}
