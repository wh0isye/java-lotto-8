package controller;

import domain.*;
import machine.LottoMachine;
import view.*;
import java.util.*;

public class LottoGameController {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoMachine lottoMachine;

    public LottoGameController() {
        this(new InputView(), new OutputView(), new LottoMachine());
    }

    public LottoGameController(InputView inputView, OutputView outputView, LottoMachine lottoMachine) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoMachine = lottoMachine;
    }

    public void run() {
        while (true) {
            try {
                long purchaseAmount = inputView.readPurchaseAmount();
                List<Lotto> lottos = lottoMachine.generateLotto(purchaseAmount);
                outputView.printPurchaseCount(lottos.size());
                outputView.printLottos(lottos);

                List<Integer> winningNumbers = inputView.readWinningNumbers();
                int bonus = inputView.readBonusNumber();

                WinningLotto winningLotto = new WinningLotto(winningNumbers, bonus);
                LottoResult result = new LottoResult();

                for (Lotto lotto : lottos) {
                    result.add(winningLotto.match(lotto));
                }

                outputView.printStatistics(result);
                outputView.printProfitRate(result.calculateProfitRate(purchaseAmount));
                break;

            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
                System.out.println("다시 입력해 주세요.\n");
            }
        }
    }
}