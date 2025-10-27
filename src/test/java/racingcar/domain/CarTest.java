package racingcar.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CarTest {
    @Test
    @DisplayName("자동차는 유효한 이름으로 생성할 수 있다.")
    void createCar_Success() {
        String name = "pobi";
        Car car = new Car(name);
        assertThat(car.getName()).isEqualTo(name);
        assertThat(car.getPosition()).isEqualTo(0); // 초기 위치는 0
    }

    @Test
    @DisplayName("자동차 이름이 5자를 초과하면 예외가 발생한다.")
    void createCar_Fail_NameExceedsLength() {
        String longName = "length";

        assertThatThrownBy(() -> new Car(longName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("5자 초과");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"  ", "\t"})
    @DisplayName("자동차 이름이 null이거나 비어있으면(공백 포함) 예외가 발생한다.")
    void createCar_Fail_NameBlank(String blankName) {
        assertThatThrownBy(() -> new Car(blankName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("빈 문자열");
    }

    @Test
    @DisplayName("move() 메서드를 호출하면 position이 1 증가한다.")
    void move_Once() {
        Car car = new Car("woni");
        car.move();
        assertThat(car.getPosition()).isEqualTo(1);
    }

    @Test
    @DisplayName("move() 메서드를 여러 번 호출하면 호출한 횟수만큼 position이 증가한다.")
    void move_MultipleTimes() {
        Car car = new Car("jun");
        int moveCount = 3;

        for (int i = 0; i < moveCount; i++) {
            car.move();
        }

        assertThat(car.getPosition()).isEqualTo(moveCount);
    }
}