package machine;

import domain.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoMachineTest {

    @Test
    @DisplayName("금액이 1,000원 미만이면 예외를 던진다")
    void validateAmount_lessThanPrice_throwsException() {
        LottoMachine machine = new LottoMachine();
        assertThatThrownBy(() -> machine.generateLotto(500))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("최소 구입 금액");
    }

    @Test
    @DisplayName("금액이 1,000원 단위가 아니면 예외를 던진다")
    void validateAmount_notMultipleOfPrice_throwsException() {
        LottoMachine machine = new LottoMachine();
        assertThatThrownBy(() -> machine.generateLotto(2500))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("단위");
    }

    @Test
    @DisplayName("금액이 1,000원 단위이면 정상적으로 로또를 생성한다")
    void generateLotto_validAmount_returnsLottos() {
        LottoMachine machine = new LottoMachine();
        List<Lotto> lottos = machine.generateLotto(3000);

        assertThat(lottos).hasSize(3);
        assertThat(lottos.get(0).getNumbers()).hasSize(6);
    }

    @Test
    @DisplayName("생성된 로또 번호는 1~45 범위의 중복 없는 정렬된 숫자 6개여야 한다")
    void generateLotto_numbersAreUniqueAndSorted() {
        LottoMachine machine = new LottoMachine();
        List<Lotto> lottos = machine.generateLotto(1000);
        List<Integer> numbers = lottos.get(0).getNumbers();

        assertThat(numbers).hasSize(6);
        assertThat(numbers).doesNotHaveDuplicates();
        assertThat(numbers).isSorted();
        assertThat(numbers).allMatch(n -> n >= 1 && n <= 45);
    }
}
