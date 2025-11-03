package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoResultTest {

    @Test
    @DisplayName("기본 생성 시 모든 Rank의 초기값은 0이다")
    void initialize_allRanksToZero() {
        LottoResult result = new LottoResult();

        Map<Rank, Integer> results = result.getResults();

        for (Rank rank : Rank.values()) {
            assertThat(results.get(rank)).isEqualTo(0);
        }
    }

    @Test
    @DisplayName("add() 호출 시 해당 Rank의 개수가 증가한다")
    void add_increasesCount() {
        LottoResult result = new LottoResult();

        result.add(Rank.FIRST);
        result.add(Rank.FIRST);
        result.add(Rank.FIFTH);

        Map<Rank, Integer> results = result.getResults();
        assertThat(results.get(Rank.FIRST)).isEqualTo(2);
        assertThat(results.get(Rank.FIFTH)).isEqualTo(1);
    }

    @Test
    @DisplayName("null Rank 추가 시 예외를 던진다")
    void add_nullRank_throwsException() {
        LottoResult result = new LottoResult();

        assertThatThrownBy(() -> result.add(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Rank는 null일 수 없습니다");
    }

    @Test
    @DisplayName("총 상금을 정확히 계산한다")
    void getTotalPrize_returnsSumOfPrizes() {
        LottoResult result = new LottoResult();

        result.add(Rank.FIRST);
        result.add(Rank.FIFTH);

        long totalPrize = result.getTotalPrize();

        assertThat(totalPrize).isEqualTo(2_000_005_000L);
    }

    @Test
    @DisplayName("구매금액이 0 이하일 경우 수익률 계산 시 예외를 던진다")
    void calculateProfitRate_withZeroOrNegativePurchaseAmount_throwsException() {
        LottoResult result = new LottoResult();

        assertThatThrownBy(() -> result.calculateProfitRate(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("구매 금액은 0보다 커야 합니다");

        assertThatThrownBy(() -> result.calculateProfitRate(-1000))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("수익률을 소수점 첫째 자리까지 정확히 계산한다")
    void calculateProfitRate_returnsProperRate() {
        LottoResult result = new LottoResult();

        result.add(Rank.FIRST);

        String profitRate = result.calculateProfitRate(10_000_000L);
        assertThat(profitRate).isEqualTo("20,000.0%");
    }

    @Test
    @DisplayName("결과 맵은 외부에서 수정할 수 없다")
    void getResults_returnsUnmodifiableMap() {
        LottoResult result = new LottoResult();

        Map<Rank, Integer> map = result.getResults();

        assertThatThrownBy(() -> map.put(Rank.FIRST, 99))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
