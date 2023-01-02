import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lotto {

    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    private static final ArrayList<Integer> list;

    static  {
        list = new ArrayList<>();
        IntStream.range(1, 45)
                .forEach(number -> list.add(number));
    }

    public static List<Integer> createLotto() {
        Collections.shuffle(list);
        return list.stream()
                .limit(6)
                .collect(Collectors.toList());
    }
}
