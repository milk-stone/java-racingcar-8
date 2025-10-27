package racingcar.controller;

import racingcar.view.InputView;
import racingcar.view.OutputView;
import racingcar.domain.RacingGame;

import java.util.List;

public class RaceController {
    private final InputView inputView;
    private final OutputView outputView;

    public RaceController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        String carNamesInput = inputView.readCarNames();
        String trialCountInput = inputView.readTrialCount();

        RacingGame game = new RacingGame(carNamesInput, trialCountInput);

        int trialCount = game.getTrialCount();

        outputView.printRaceResultHeader();
        for (int i = 0; i < trialCount; i++) {
            game.runRound();
            outputView.printRoundResult(game.getCars());
        }

        List<String> winners = game.getWinnerNames();
        outputView.printWinners(winners);
    }
}
