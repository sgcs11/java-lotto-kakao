package domain.util.validation;

import domain.LottoNumber;
import domain.util.validation.annotation.SixNumbers;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class LottoValidator {

    public static void validate(Object obj) {
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        Method[] declaredMethods = obj.getClass().getDeclaredMethods();

        validSixNumbers(obj, declaredFields);
    }

    private static void validSixNumbers(final Object obj, final Field[] declaredFields) {
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
