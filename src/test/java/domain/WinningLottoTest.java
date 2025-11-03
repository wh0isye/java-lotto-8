package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningLottoTest {

    @Test
    @DisplayName("당첨 번호가 6개가 아니면 예외를 던진다")
    void validate_numberCount_shouldThrowException() {
        List<Integer> invalidNumbers = List.of(1, 2, 3, 4, 5); // 5개
        assertThatThrownBy(() -> new WinningLotto(invalidNumbers, 7))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("6개여야");
    }

    @Test
    @DisplayName("당첨 번호가 중복되면 예외를 던진다")
    void validate_duplicateNumbers_shouldThrowException() {
        List<Integer> invalidNumbers = List.of(1, 2, 3, 3, 4, 5);
        assertThatThrownBy(() -> new WinningLotto(invalidNumbers, 7))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복");
    }

    @Test
    @DisplayName("보너스 번호가 1~45 범위를 벗어나면 예외를 던진다")
    void validate_bonusOutOfRange_shouldThrowException() {
        List<Integer> validNumbers = List.of(1, 2, 3, 4, 5, 6);
        assertThatThrownBy(() -> new WinningLotto(validNumbers, 46))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1부터 45 사이");
    }

    @Test
    @DisplayName("보너스 번호가 당첨 번호와 중복되면 예외를 던진다")
    void validate_bonusDuplicate_shouldThrowException() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        assertThatThrownBy(() -> new WinningLotto(numbers, 6))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복");
    }

    @Test
    @DisplayName("6개 번호가 모두 일치하면 1등을 반환한다")
    void match_sixNumbers_returnsFirst() {
        WinningLotto winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        Rank result = winningLotto.match(lotto);

        assertThat(result).isEqualTo(Rank.FIRST);
    }

    @Test
    @DisplayName("5개 번호와 보너스 번호가 일치하면 2등을 반환한다")
    void match_fiveNumbersWithBonus_returnsSecond() {
        WinningLotto winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 7));

        Rank result = winningLotto.match(lotto);

        assertThat(result).isEqualTo(Rank.SECOND);
    }

    @Test
    @DisplayName("5개 번호만 일치하면 3등을 반환한다")
    void match_fiveNumbersWithoutBonus_returnsThird() {
        WinningLotto winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 8));

        Rank result = winningLotto.match(lotto);

        assertThat(result).isEqualTo(Rank.THIRD);
    }

    @Test
    @DisplayName("4개 일치하면 4등을 반환한다")
    void match_fourNumbers_returnsFourth() {
        WinningLotto winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 8, 9));

        Rank result = winningLotto.match(lotto);

        assertThat(result).isEqualTo(Rank.FOURTH);
    }

    @Test
    @DisplayName("3개 일치하면 5등을 반환한다")
    void match_threeNumbers_returnsFifth() {
        WinningLotto winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);
        Lotto lotto = new Lotto(List.of(1, 2, 3, 8, 9, 10));

        Rank result = winningLotto.match(lotto);

        assertThat(result).isEqualTo(Rank.FIFTH);
    }

    @Test
    @DisplayName("2개 이하 일치 시 낙첨을 반환한다")
    void match_lessThanThree_returnsNone() {
        WinningLotto winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);
        Lotto lotto = new Lotto(List.of(1, 9, 10, 11, 12, 13));

        Rank result = winningLotto.match(lotto);

        assertThat(result).isEqualTo(Rank.NONE);
    }
}
