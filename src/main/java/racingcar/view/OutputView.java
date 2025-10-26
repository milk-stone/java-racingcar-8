package racingcar.view;

import racingcar.domain.Car;

import java.util.List;

public class OutputView {
    private static final String POSITION_SYMBOL = "-";
    private static final String WINNER_SEPARATOR = ", ";

    public void printRaceResultHeader() {
        System.out.println("\n실행 결과");
    }

    public void printRoundResult(List<Car> cars) {
        for (Car car : cars) {
            String positionDisplay = buildPositionDisplay(car.getPosition());
            System.out.println(car.getName() + " : " + positionDisplay);
        }
        System.out.println();
    }

    private String buildPositionDisplay(int position) {
        return POSITION_SYMBOL.repeat(position);
    }

    public void printWinners(List<String> winnerNames) {
        String winners = String.join(WINNER_SEPARATOR, winnerNames);
        System.out.println("\n최종 우승자 : " + winners);
    }
}
