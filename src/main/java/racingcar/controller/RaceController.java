package racingcar.controller;

import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RaceController {
    private InputView inputView;
    private OutputView outputView;

    public RaceController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }
}
