import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lottos {
    private final List<Lotto> lottoList;

    private final int count;

    private final int totalPay;

    Lottos(List<Lotto> lottoList, int totalPay) {
        this.lottoList = lottoList;
        this.count = lottoList.size();
        this.totalPay = totalPay;
    }

    public List<Lotto> getLottoList() {
        return lottoList;
    }

    public int getCount() {
        return count;
    }

    public int getTotalPay() {
        return totalPay;
    }

    public double getTotalLotteryAmount(final WinLottoNumbers winNumbers) {
        double result = 0;
        for(Lotto lotto : lottoList) {
            result += lotto.getLotteryAmount((int)lotto.getMatchCount(winNumbers.getNumbers()), lotto.isMatchBonusNumber(winNumbers.getBonusNumber()));
        }
        return result;
    }

    public Map<Integer, Integer> getTotalResult(final WinLottoNumbers winNumbers) {
        Map<Integer, Integer> result = new HashMap<>();

        for(Lotto lotto : lottoList) {
            int lotteryResult = lotto.getLotteryResult((int)lotto.getMatchCount(winNumbers.getNumbers()), lotto.isMatchBonusNumber(winNumbers.getBonusNumber()));
            result.put(lotteryResult, result.getOrDefault(lotteryResult, 0) + 1);
        }
      return result;
    }

    public double getTotalLotteryRate(final double amount, final double purchaseAmount) {
        return amount/purchaseAmount;
    }
}
