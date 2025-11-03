package domain;

import java.util.*;

public class WinningLotto {
    private final List<Integer> numbers;
    private final int bonus;

    public WinningLotto(List<Integer> numbers, int bonus) {
        validate(numbers, bonus);
        this.numbers = new ArrayList<>(numbers);
        this.bonus = bonus;
    }

    private void validate(List<Integer> numbers, int bonus) {
        if (numbers.size() != 6)
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 6개여야 합니다.");
        if (new HashSet<>(numbers).size() != 6)
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 중복될 수 없습니다.");
        if (bonus < 1 || bonus > 45)
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 1부터 45 사이여야 합니다.");
        if (numbers.contains(bonus))
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
    }

    public Rank match(Lotto lotto) {
        int matchCount = lotto.countMatchingNumbers(numbers);
        boolean bonusMatch = lotto.contains(bonus);
        return Rank.of(matchCount, bonusMatch);
    }
}
