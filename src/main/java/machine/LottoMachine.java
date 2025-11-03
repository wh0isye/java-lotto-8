package machine;

import domain.Lotto;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoMachine {
    private static final int PRICE = 1000;

    public List<Lotto> generateLotto(long amount) {
        validateAmount(amount);

        int count = (int) (amount / PRICE);
        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            Collections.sort(numbers);
            lottos.add(new Lotto(numbers));
        }

        return lottos;
    }

    private void validateAmount(long amount) {
        if (amount < PRICE) {
            throw new IllegalArgumentException(
                    String.format("[ERROR] 최소 구입 금액은 %,d원 이상이어야 합니다.", PRICE)
            );
        }
        if (amount % PRICE != 0) {
            throw new IllegalArgumentException(
                    String.format("[ERROR] 구입 금액은 %,d원 단위여야 합니다.", PRICE)
            );
        }
    }
}
