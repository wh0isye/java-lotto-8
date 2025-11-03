package util;

import java.util.*;
import java.util.stream.Collectors;

public class NumbersParser {

    public static List<Integer> parseCsv(String input) {
        try {
            List<Integer> numbers = Arrays.stream(input.split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            validate(numbers);
            return numbers;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 쉼표로 구분된 숫자여야 합니다.");
        }
    }

    private static void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 6개여야 합니다.");
        }
        if (new HashSet<>(numbers).size() != 6) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 중복될 수 없습니다.");
        }
        if (numbers.stream().anyMatch(n -> n < 1 || n > 45)) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 1부터 45 사이여야 합니다.");
        }
    }
}