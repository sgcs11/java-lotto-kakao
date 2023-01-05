package domain.util;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoValidator {

    public static void validate(Class<?> clazz) {
        Method[] methods = clazz.getDeclaredMethods();

        List<Parameter[]> parameters = Arrays.stream(methods)
                .filter(method -> method.isAnnotationPresent(LottoValid.class)).map(Method::getParameters).collect(Collectors.toList());

        parameters.forEach(System.out::println);
    }
}
