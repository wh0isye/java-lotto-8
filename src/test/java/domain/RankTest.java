package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RankTest {

    @Test
    @DisplayName("6개 일치 시 1등을 반환한다")
    void matchSixNumbers_returnsFirst() {
        Rank rank = Rank.of(6, false);
        assertThat(rank).isEqualTo(Rank.FIRST);
        assertThat(rank.getPrize()).isEqualTo(2_000_000_000L);
    }

    @Test
    @DisplayName("5개 + 보너스 번호 일치 시 2등을 반환한다")
    void matchFiveNumbersWithBonus_returnsSecond() {
        Rank rank = Rank.of(5, true);
        assertThat(rank).isEqualTo(Rank.SECOND);
        assertThat(rank.getPrize()).isEqualTo(30_000_000L);
        assertThat(rank.isBonus()).isTrue();
    }

    @Test
    @DisplayName("5개만 일치 시 3등을 반환한다")
    void matchFiveNumbersWithoutBonus_returnsThird() {
        Rank rank = Rank.of(5, false);
        assertThat(rank).isEqualTo(Rank.THIRD);
        assertThat(rank.getPrize()).isEqualTo(1_500_000L);
    }

    @Test
    @DisplayName("4개 일치 시 4등을 반환한다")
    void matchFourNumbers_returnsFourth() {
        Rank rank = Rank.of(4, false);
        assertThat(rank).isEqualTo(Rank.FOURTH);
        assertThat(rank.getPrize()).isEqualTo(50_000L);
    }

    @Test
    @DisplayName("3개 일치 시 5등을 반환한다")
    void matchThreeNumbers_returnsFifth() {
        Rank rank = Rank.of(3, false);
        assertThat(rank).isEqualTo(Rank.FIFTH);
        assertThat(rank.getPrize()).isEqualTo(5_000L);
    }

    @Test
    @DisplayName("2개 이하 일치 시 NONE(낙첨)을 반환한다")
    void matchLessThanThree_returnsNone() {
        assertThat(Rank.of(2, false)).isEqualTo(Rank.NONE);
        assertThat(Rank.of(1, false)).isEqualTo(Rank.NONE);
        assertThat(Rank.of(0, false)).isEqualTo(Rank.NONE);
    }

    @Test
    @DisplayName("toString()은 일치 개수와 보너스 여부, 금액을 포함하여 반환한다")
    void toStringFormatsProperly() {
        assertThat(Rank.FIRST.toString()).isEqualTo("6개 일치 (2,000,000,000원)");
        assertThat(Rank.SECOND.toString()).isEqualTo("5개 일치, 보너스 볼 일치 (30,000,000원)");
        assertThat(Rank.NONE.toString()).isEqualTo("꽝");
    }
}