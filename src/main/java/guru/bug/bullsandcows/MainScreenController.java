package guru.bug.bullsandcows;

import javafx.event.ActionEvent;
import javafx.scene.control.Spinner;

public class MainScreenController {
    public Spinner<Integer> numSpinner1;
    public Spinner<Integer> numSpinner2;
    public Spinner<Integer> numSpinner3;
    public Spinner<Integer> numSpinner4;

    public void doTurn() {
        var n1 = numSpinner1.getValue();
        var n2 = numSpinner2.getValue();
        var n3 = numSpinner3.getValue();
        var n4 = numSpinner4.getValue();
        System.out.printf("TURN %d%d%d%d%n", n1, n2, n3, n4);
    }
}
