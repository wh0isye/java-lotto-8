package domain;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class LottoResult {
    private final EnumMap<Rank, Integer> results = new EnumMap<>(Rank.class);

    public LottoResult() {
        for (Rank rank : Rank.values()) {
            results.put(rank, 0);
        }
    }

    public void add(Rank rank) {
        if (rank == null) {
            throw new IllegalArgumentException("[ERROR] Rank는 null일 수 없습니다.");
        }
        results.put(rank, results.get(rank) + 1);
    }

    public long getTotalPrize() {
        return results.entrySet().stream()
                .mapToLong(e -> e.getKey().getPrize() * e.getValue())
                .sum();
    }

    public String calculateProfitRate(long purchaseAmount) {
        if (purchaseAmount <= 0) {
            throw new IllegalArgumentException("[ERROR] 구매 금액은 0보다 커야 합니다.");
        }

        double rate = ((double) getTotalPrize() / purchaseAmount) * 100;
        DecimalFormat df = new DecimalFormat("#,##0.0");
        return df.format(rate) + "%";
    }

    public Map<Rank, Integer> getResults() {
        return Collections.unmodifiableMap(results);
    }
}
