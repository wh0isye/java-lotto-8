package util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class NumbersParserTest {

    @Test
    @DisplayName("쉼표로 구분된 6개의 숫자를 정상적으로 파싱한다")
    void parseCsv_validInput_returnsList() {
        List<Integer> result = NumbersParser.parseCsv("1,2,3,4,5,6");
        assertThat(result).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @Test
    @DisplayName("숫자가 아닌 입력이 포함된 경우 예외를 던진다")
    void parseCsv_nonNumeric_throwsException() {
        assertThatThrownBy(() -> NumbersParser.parseCsv("1,2,a,4,5,6"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("숫자");
    }

    @Test
    @DisplayName("숫자가 6개 미만인 경우 예외를 던진다")
    void parseCsv_lessThanSix_throwsException() {
        assertThatThrownBy(() -> NumbersParser.parseCsv("1,2,3,4,5"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("6개여야");
    }

    @Test
    @DisplayName("숫자가 6개 초과인 경우 예외를 던진다")
    void parseCsv_moreThanSix_throwsException() {
        assertThatThrownBy(() -> NumbersParser.parseCsv("1,2,3,4,5,6,7"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("6개여야");
    }

    @Test
    @DisplayName("중복된 숫자가 포함된 경우 예외를 던진다")
    void parseCsv_duplicateNumbers_throwsException() {
        assertThatThrownBy(() -> NumbersParser.parseCsv("1,2,2,4,5,6"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복");
    }

    @Test
    @DisplayName("숫자가 1~45 범위를 벗어나면 예외를 던진다")
    void parseCsv_outOfRange_throwsException() {
        assertThatThrownBy(() -> NumbersParser.parseCsv("0,2,3,4,5,46"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1부터 45 사이");
    }
}
