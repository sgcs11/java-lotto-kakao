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
        isValidNumber(lottoNumber);

        if (Integer.parseInt(lottoNumber) < 1 || Integer.parseInt(lottoNumber) > 45) {
            throw new RuntimeException("유효한 번호가 입력되지 않았습니다.(유효한 번호: 1 ~ 45)");
        }
    }

    public static void isValidAmount(String amount) {
        isValidNumber(amount);

        if (Integer.parseInt(amount) < 0 || Integer.parseInt(amount) % 1000 != 0) {
            throw new RuntimeException("금액은 0보다 큰 1000의 배수여야 합니다.");
        }
    }

    public static void isValidManualCount(String manualCount) {
        isValidNumber(manualCount);

        if(Integer.parseInt(manualCount) < 0) {
            throw new RuntimeException("수동으로 구매할 로또 수가 음수입니다.");
        }
    }

    public static void isValidNumber(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new RuntimeException("입력이 정수 형태가 아닙니다.");
        }
    }
}
