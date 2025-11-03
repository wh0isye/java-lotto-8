package domain;

public enum Rank {
    FIRST(6, false, 2_000_000_000L),
    SECOND(5, true, 30_000_000L),
    THIRD(5, false, 1_500_000L),
    FOURTH(4, false, 50_000L),
    FIFTH(3, false, 5_000L),
    NONE(0, false, 0L);

    private final int matchCount;
    private final boolean bonus;
    private final long prize;

    Rank(int matchCount, boolean bonus, long prize) {
        this.matchCount = matchCount;
        this.bonus = bonus;
        this.prize = prize;
    }

    public static Rank of(int matchCount, boolean bonusMatch) {
        if (matchCount == 6) {
            return FIRST;
        }
        if (matchCount == 5 && bonusMatch) {
            return SECOND;
        }
        if (matchCount == 5) {
            return THIRD;
        }
        if (matchCount == 4) {
            return FOURTH;
        }
        if (matchCount == 3) {
            return FIFTH;
        }
        return NONE;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public boolean isBonus() {
        return bonus;
    }

    public long getPrize() {
        return prize;
    }

    @Override
    public String toString() {
        if (this == NONE) {
            return "꽝";
        }
        String bonusText = bonus ? ", 보너스 볼 일치" : "";
        return String.format("%d개 일치%s (%s원)",
                matchCount, bonusText, String.format("%,d", prize));
    }
}