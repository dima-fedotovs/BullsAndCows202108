package guru.bug.bullsandcows;

import javafx.beans.binding.Bindings;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.*;

public class MainScreenController implements Initializable {
    public Spinner<Integer> numSpinner1;
    public Spinner<Integer> numSpinner2;
    public Spinner<Integer> numSpinner3;
    public Spinner<Integer> numSpinner4;
    public TableView<TurnResult> turnTable;
    public Button goButton;
    private int turnCount;
    private List<Integer> myNum;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        goButton.disableProperty().bind(Bindings.createBooleanBinding(() -> {
                    var nums = new HashSet<Integer>();
                    nums.add(numSpinner1.getValue());
                    nums.add(numSpinner2.getValue());
                    nums.add(numSpinner3.getValue());
                    nums.add(numSpinner4.getValue());
                    return nums.size() < 4;
                },
                numSpinner1.valueProperty(),
                numSpinner2.valueProperty(),
                numSpinner3.valueProperty(),
                numSpinner4.valueProperty()
        ));

        var rand = new Random();
        var tmp = new LinkedHashSet<Integer>();
        while (tmp.size() < 4) {
            var n = rand.nextInt(10);
            var added = tmp.add(n);
//            System.out.printf("%d - added:%s %n", n, added);
        }
        myNum = List.copyOf(tmp);
        System.out.println(myNum);
    }

    private int countBulls(List<Integer> userNum) {
        var bulls = 0;
        for (int idx = 0; idx < 4; idx++) {
            int myNumValue = myNum.get(idx);
            int userNumValue = userNum.get(idx);
            if (myNumValue == userNumValue) {
                bulls++;
            }
        }
        return bulls;
    }

    private int countCows(List<Integer> userNum) {
        var cows = 0;
        for (int userNumIdx = 0; userNumIdx < 4; userNumIdx++) {
            int userNumValue = userNum.get(userNumIdx);
            for (int myNumIdx = 0; myNumIdx < 4; myNumIdx++) {
                int myNumValue = myNum.get(myNumIdx);
                if (myNumValue == userNumValue && myNumIdx != userNumIdx) {
                    cows++;
                }
            }
        }
        return cows;
    }

    private int[] countBullsAndCows(List<Integer> userNum) {
        var bulls = 0;
        var cows = 0;
        for (int userNumIdx = 0; userNumIdx < 4; userNumIdx++) {
            int userNumValue = userNum.get(userNumIdx);
            for (int myNumIdx = 0; myNumIdx < 4; myNumIdx++) {
                int myNumValue = myNum.get(myNumIdx);
                if (myNumValue == userNumValue) {
                    if (myNumIdx == userNumIdx) {
                        bulls++;
                    } else {
                        cows++;
                    }
                }
            }
        }
        return new int[]{bulls, cows};
    }

    public void doTurn() {
        turnCount++;
        var n1 = numSpinner1.getValue();
        var n2 = numSpinner2.getValue();
        var n3 = numSpinner3.getValue();
        var n4 = numSpinner4.getValue();
        var guess = "" + n1 + n2 + n3 + n4;

        var userNum = List.of(n1, n2, n3, n4);
        var bullsAndCows = countBullsAndCows(userNum);
        var bulls = bullsAndCows[0];
        var cows = bullsAndCows[1];

        var turn = new TurnResult();
        turn.setGuess(guess);
        turn.setNr(turnCount);
        turn.setBulls(bulls);
        turn.setCows(cows);

        turnTable.getItems().add(0, turn);
    }
}
