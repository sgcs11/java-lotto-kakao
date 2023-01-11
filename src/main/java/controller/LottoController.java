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
        String totalIssueCost = inputAndValidAmount();

        Lottos manualIssueLottos = manualIssueProcess();
        Lottos autoIssueLottos = autoIssueProcess(Integer.parseInt(totalIssueCost), LottoPrice.LOTTO_NORMAL_PRICE.get() * manualIssueLottos.getLottoList().size());
        LottoOutputView.printLottos(manualIssueLottos, autoIssueLottos);

        mergeLottos(manualIssueLottos, autoIssueLottos);
    }

    private static String inputAndValidAmount() {
        String totalIssueCost = LottoInputView.inputAmount();
        LottoValidator.isValidAmount(totalIssueCost);
        return totalIssueCost;
    }

    private static void mergeLottos(final Lottos manualIssueLottos, final Lottos autoIssueLottos) {
        Lottos lottos = LottoFactory.createLottos(autoIssueLottos, manualIssueLottos);
        resultProcess(lottos);
    }

    private static Lottos autoIssueProcess(int totalIssueCost, int manualIssueCost) {
        return LottoFactory.createAutoLottos(totalIssueCost - manualIssueCost);
    }

    private static Lottos manualIssueProcess() {
        String manualCount = inputAndValidManualCount();
        List<String> inputManualLottoNumbers = LottoInputView.inputManualLottoNumbers(Integer.parseInt(manualCount));
        inputManualLottoNumbers.forEach(LottoValidator::isValidSixNumbers);

        return LottoFactory.createManualLottos(inputManualLottoNumbers
                .stream()
                .map(Parser::parsingLottoNumbers)
                .map(LottoFactory::createManualLotto)
                .collect(Collectors.toList()));
    }

    private static String inputAndValidManualCount() {
        String manualCount = LottoInputView.inputManualCount();
        LottoValidator.isValidManualCount(manualCount);
        return manualCount;
    }

    private static void resultProcess(final Lottos lottos) {
        Set<LottoNumber> lottoNumbers = inputAndValidWinNumbers();

        String inputBonusNumber = inputAndValidBonusNumber();

        LottoNumber bonusLottoNumber = LottoNumber.getLottoNumber(Integer.parseInt(inputBonusNumber));
        WinLottoNumbers winLottoNumbers = new WinLottoNumbers(lottoNumbers, bonusLottoNumber);

        LottoOutputView.printResult(lottos, winLottoNumbers);
    }

    private static String inputAndValidBonusNumber() {
        String inputBonusNumber = LottoInputView.inputBonusNumber();
        LottoValidator.isValidLottoNumber(inputBonusNumber);
        return inputBonusNumber;
    }

    private static Set<LottoNumber> inputAndValidWinNumbers() {
        String inputWinNumbers = LottoInputView.inputWinNumbers();
        LottoValidator.isValidSixNumbers(inputWinNumbers);
        Set<LottoNumber> lottoNumbers = Parser.parsingLottoNumbers(inputWinNumbers);
        return lottoNumbers;
    }
}
