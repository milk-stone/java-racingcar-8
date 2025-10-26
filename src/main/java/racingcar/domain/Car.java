package racingcar.domain;

public class Car {
    private final String name;
    private int position = 0;

    public Car(String name) {
        validateName(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void move() {
        position++;
    }

    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("잘못된 입력 값 : 빈 문자열");
        }
        if (name.length() > 5) {
            throw new IllegalArgumentException("잘못된 입력 값 : 이름 5자 초과");
        }
    }
}
