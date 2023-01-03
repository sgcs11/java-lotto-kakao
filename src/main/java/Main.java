import domain.LottoFactory;
import domain.LottoNumber;
import domain.Lottos;
import domain.WinLottoNumbers;
import util.Parser;
import view.View;

public class Main {
    public static void main(String[] args) {
        Lottos lottos = LottoFactory.createLottos(View.inputAmount());
        View.printLottos(lottos);

        String input = View.inputWinNumbers();
        WinLottoNumbers winLottoNumbers = Parser.parsingWinNumbers(input);
        winLottoNumbers.setBonusNumber(LottoNumber.getLottoNumber(View.inputBounsNumber()));

        View.printResult(lottos, winLottoNumbers);
    }
}
