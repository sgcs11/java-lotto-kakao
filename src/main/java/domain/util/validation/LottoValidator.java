package domain.util.validation;

import domain.Lotto;
import domain.LottoNumber;
import domain.Lottos;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class LottoValidator {

    public static void validate(Object obj) {
        Field[] declaredFields = obj.getClass().getDeclaredFields();

        List<Field> fields = Arrays.stream(declaredFields)
                .filter(field -> field.isAnnotationPresent(SixNumbers.class))
                .collect(Collectors.toList());

        for (Field field : fields) {
            field.setAccessible(true);

            try {
                HashSet<LottoNumber> lottoNumbers = (HashSet<LottoNumber>) field.get(obj);
                if (lottoNumbers.size() != 6){
                    throw new RuntimeException("로또 번호가 6개가 아닙니다.");
                }

            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
