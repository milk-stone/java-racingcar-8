package racingcar.view;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.Application;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

class ViewTest extends NsTest {

    @Test
    @DisplayName("자동차 이름 입력 테스트")
    void inputCarNamesTest() {
        assertSimpleTest(() -> {
            run("pobi,woni,jun", "1");

            assertThat(output()).contains("경주할 자동차 이름을 입력하세요.");
        });
    }

    @Test
    @DisplayName("시도 횟수 입력 테스트")
    void inputTrialCountTest() {
        assertSimpleTest(() -> {
            run("pobi", "5");

            assertThat(output()).contains("시도할 횟수는 몇 회인가요?");
        });
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}