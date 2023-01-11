package domain.util.validation;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoValidator {

    public static void isValidSixNumbers(String lottoNumbers) {
        List<String> lottoNumberList = Arrays.stream(lottoNumbers.split(","))
                .distinct()
                .collect(Collectors.toList());

        if (lottoNumberList.size() != 6) {
            throw new RuntimeException("입력된 숫자가 6개가 아닙니다.");
        }

        lottoNumberList.forEach(LottoValidator::isValidLottoNumber);
    }

    public static void isValidLottoNumber(String lottoNumber) {
        int validLottoNumber = isValidNumber(lottoNumber);

        if (validLottoNumber < 1 || validLottoNumber > 45) {
            throw new RuntimeException("유효한 번호가 입력되지 않았습니다.(유효한 번호: 1 ~ 45)");
        }
    }

    public static void isValidAmount(String amount) {
        int validAmount = isValidNumber(amount);

        if (validAmount < 0 || validAmount % 1000 != 0) {
            throw new RuntimeException("금액은 0보다 큰 1000의 배수여야 합니다.");
        }
    }

    public static void isValidManualCount(String manualCount) {
        int validmanualCount = isValidNumber(manualCount);

        if(validmanualCount < 0) {
            throw new RuntimeException("수동으로 구매할 로또 수가 음수입니다.");
        }
    }

    public static int isValidNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new RuntimeException("입력이 정수 형태가 아닙니다.");
        }
    }
}
