package racingcar.domain;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RacingGame {
    private static final String NAME_SEPARATOR = ",";
    private static final int MOVE_THRESHOLD = 4;

    private final List<Car> cars;
    private final int trialCount;

    public RacingGame(String carNamesInput, String trialCount) {
        this.trialCount = parseAndValidateTrialCount(trialCount);
        this.cars = createCarsFromNames(carNamesInput);
    }

    public List<Car> getCars() {
        return Collections.unmodifiableList(cars);
    }

    public int getTrialCount() {
        return trialCount;
    }

    private List<Car> createCarsFromNames(String carNamesInput) {
        List<Car> cars = new ArrayList<>();
        for (String name : carNamesInput.split(NAME_SEPARATOR)) {
            Car car = new Car(name.strip());
            cars.add(car);
        }
        return cars;
    }

    private int parseAndValidateTrialCount(String trialCountInput) {
        int trialCount;
        try {
            trialCount = Integer.parseInt(trialCountInput);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("시도 횟수는 숫자여야 합니다.");
        }

        if (trialCount <= 0) {
            throw new IllegalArgumentException("시도 횟수는 1 이상이어야 합니다.");
        }

        return trialCount;
    }

    private boolean shouldMove() {
        int randomNumber = Randoms.pickNumberInRange(0, 9);
        return randomNumber >= MOVE_THRESHOLD;
    }


    public void runRound() {
        for (Car car : cars) {
            if (shouldMove()) {
                car.move();
            }
        }
    }
}
