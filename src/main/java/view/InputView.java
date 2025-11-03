package view;

import camp.nextstep.edu.missionutils.Console;
import util.NumbersParser;
import java.util.List;

public class InputView {

    public long readPurchaseAmount() {
        System.out.println("구입 금액을 입력해 주세요.");
        String input = Console.readLine().trim();

        if (input.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 숫자여야 합니다.");
        }

        try {
            long amount = Long.parseLong(input);
            if (amount < 1000) {
                throw new IllegalArgumentException("[ERROR] 최소 구입 금액은 1,000원 이상이어야 합니다.");
            }
            if (amount % 1000 != 0) {
                throw new IllegalArgumentException("[ERROR] 구입 금액은 1,000원 단위여야 합니다.");
            }
            return amount;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 숫자여야 합니다.");
        }
    }

    public List<Integer> readWinningNumbers() {
        System.out.println("당첨 번호를 입력해 주세요.");
        String input = Console.readLine().trim();

        if (input.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호를 입력해야 합니다.");
        }

        return NumbersParser.parseCsv(input);
    }

    public int readBonusNumber() {
        System.out.println("보너스 번호를 입력해 주세요.");
        String input = Console.readLine().trim();

        if (input.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 숫자여야 합니다.");
        }

        try {
            int number = Integer.parseInt(input);
            if (number < 1 || number > 45) {
                throw new IllegalArgumentException("[ERROR] 보너스 번호는 1부터 45 사이의 숫자여야 합니다.");
            }
            return number;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 숫자여야 합니다.");
        }
    }
}