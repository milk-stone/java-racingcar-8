package racingcar.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RacingGameTest {

    @Test
    @DisplayName("정상적인 입력값으로 RacingGame 객체를 생성한다.")
    void createGame_Success() {
        String carNames = "pobi,woni,jun";
        String trialCount = "5";

        RacingGame game = new RacingGame(carNames, trialCount);

        assertThat(game.getTrialCount()).isEqualTo(5);
        assertThat(game.getCars())
                .hasSize(3)
                .extracting(Car::getName)
                .containsExactly("pobi", "woni", "jun");
    }

    @Test
    @DisplayName("입력된 자동차 이름의 앞뒤 공백을 제거(strip)한다.")
    void createGame_WithSpaces() {
        String carNames = "  pobi , woni,jun  ";
        String trialCount = "1";

        RacingGame game = new RacingGame(carNames, trialCount);

        assertThat(game.getCars())
                .extracting(Car::getName)
                .containsExactly("pobi", "woni", "jun");
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", " ", "", "1.5"})
    @DisplayName("시도 횟수가 숫자가 아닐 경우 예외가 발생한다.")
    void createGame_Fail_TrialCountNotNumber(String invalidInput) {
        String carNames = "pobi,woni";

        assertThatThrownBy(() -> new RacingGame(carNames, invalidInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("숫자여야 합니다");
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "-1", "-100"})
    @DisplayName("시도 횟수가 1 미만일 경우 예외가 발생한다.")
    void createGame_Fail_TrialCountNegative(String invalidInput) {
        String carNames = "pobi,woni";

        assertThatThrownBy(() -> new RacingGame(carNames, invalidInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1 이상이어야 합니다");
    }

    @Test
    @DisplayName("Car의 유효성 검증(이름 5자 초과)이 실패하면 예외가 발생한다.")
    void createGame_Fail_CarNameValidation() {
        String carNames = "pobi,length";
        String trialCount = "1";

        assertThatThrownBy(() -> new RacingGame(carNames, trialCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("5자 초과");
    }

    @Test
    @DisplayName("getWinnerNames() - 게임 시작 직후 (아무도 움직이지 않았을 때) 모두가 우승자이다.")
    void getWinnerNames_InitialState_AllWinners() {
        String carNames = "pobi,woni,jun";
        RacingGame game = new RacingGame(carNames, "5");

        List<String> winners = game.getWinnerNames();

        assertThat(winners).containsExactlyInAnyOrder("pobi", "woni", "jun");
    }
}