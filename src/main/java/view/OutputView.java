package view;

import domain.*;
import java.util.List;
import java.util.Map;

public class OutputView {

    public void printPurchaseCount(int count) {
        System.out.println(count + "개를 구매했습니다.");
    }

    public void printLottos(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            System.out.println(lotto.getNumbers());
        }
    }

    public void printStatistics(LottoResult result) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---");

        Map<Rank, Integer> map = result.getResults();
        System.out.printf("3개 일치 (%,d원) - %d개%n", Rank.FIFTH.getPrize(), map.getOrDefault(Rank.FIFTH, 0));
        System.out.printf("4개 일치 (%,d원) - %d개%n", Rank.FOURTH.getPrize(), map.getOrDefault(Rank.FOURTH, 0));
        System.out.printf("5개 일치 (%,d원) - %d개%n", Rank.THIRD.getPrize(), map.getOrDefault(Rank.THIRD, 0));
        System.out.printf("5개 일치, 보너스 볼 일치 (%,d원) - %d개%n", Rank.SECOND.getPrize(), map.getOrDefault(Rank.SECOND, 0));
        System.out.printf("6개 일치 (%,d원) - %d개%n", Rank.FIRST.getPrize(), map.getOrDefault(Rank.FIRST, 0));
    }

    public void printProfitRate(String rate) {
        System.out.println("총 수익률은 " + rate + "입니다.");
    }

    public void printError(String message) {
        if (!message.startsWith("[ERROR]")) {
            System.out.println("[ERROR] " + message);
        } else {
            System.out.println(message);
        }
    }
}
