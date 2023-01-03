package domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lottos {
    private final List<Lotto> lottoList;

    private final int lottoCount;

    private final int totalPay;

    public Lottos(List<Lotto> lottoList) {
        this.lottoList = lottoList;
        this.lottoCount = lottoList.size();
        this.totalPay = lottoList.size() * LottoInfo.LOTTO_PRICE.getValue();
    }

    public List<Lotto> getLottoList() {
        return lottoList;
    }

    public int getLottoCount() {
        return lottoCount;
    }

    public int getTotalPay() {
        return totalPay;
    }

    public int getTotalLotteryAmount(final WinLottoNumbers winNumbers) {
        int result = 0;

        for(Lotto lotto : lottoList) {
            int rank = getEachRank(winNumbers, lotto);
            result += lotto.getLotteryAmount(rank);
        }

        return result;
    }

    private static int getEachRank(final WinLottoNumbers winNumbers, final Lotto lotto) {
        int lottoMatchCount = lotto.getMatchCount(winNumbers.getLottoNumbers());
        boolean isMatchBonus = lotto.isMatchBonusNumber(winNumbers.getBonusNumber());

        int rank = lotto.getLotteryRank(lottoMatchCount, isMatchBonus);
        return rank;
    }

    public Map<Integer, Integer> getTotalResult(final WinLottoNumbers winNumbers) {
        Map<Integer, Integer> result = new HashMap<>();

        for(Lotto lotto : lottoList) {
            int lotteryResult = lotto.getLotteryRank(lotto.getMatchCount(winNumbers.getLottoNumbers()), lotto.isMatchBonusNumber(winNumbers.getBonusNumber()));
            result.put(lotteryResult, result.getOrDefault(lotteryResult, 0) + 1);
        }
      return result;
    }

    public double getTotalLotteryRate(final int amount, final int purchaseAmount) {
        return (double)amount/purchaseAmount;
    }
}
