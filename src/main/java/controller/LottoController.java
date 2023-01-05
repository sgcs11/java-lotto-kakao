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
        int totalIssueCost = LottoInputView.inputAmount();

        if (totalIssueCost < 0) throw new RuntimeException("금액은 0보다 크거나 같아야 합니다.");

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
        Set<LottoNumber> lottoNumbers = Parser.parsingLottoNumbers(LottoInputView.inputWinNumbers());

        int inputBounsNumber = LottoInputView.inputBounsNumber();
        if (inputBounsNumber < 0) throw new RuntimeException("보너스 번호는 1~45사이어야 합니다.");
        LottoNumber bonusLottoNumber = LottoNumber.getLottoNumber(inputBounsNumber);
        WinLottoNumbers winLottoNumbers = new WinLottoNumbers(lottoNumbers, bonusLottoNumber);
        LottoValidator.validate(winLottoNumbers);

        LottoOutputView.printResult(lottos, winLottoNumbers);
    }
}
