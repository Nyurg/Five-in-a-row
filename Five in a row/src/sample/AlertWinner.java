package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertWinner {

    private static boolean answer = false;

    public static boolean display(int playerNumber) {
        // window properties
        Stage window = new Stage();
        window.setTitle("Congratulations!");
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(250);

        // label properties
        Label winnerLabel = new Label();
        if (playerNumber == 1) {
            winnerLabel.setText("Congratulations, Trash Man!");
        } else {
            winnerLabel.setText("Congratulations, Dr. Toboggan!");
        }
        Label messageLabel = new Label("What would you like to do?");

        // button properties
        Button yesButton = new Button("Play again");
        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
        });
        Button noButton = new Button("Close the application.");
        noButton.setOnAction(e -> {
            answer = false;
            window.close();
        });

        // scene properties
        VBox layout = new VBox(10);
        layout.getChildren().addAll(winnerLabel, messageLabel, yesButton, noButton);
        layout.setAlignment(Pos.CENTER);

        window.setScene(new Scene(layout));
        window.showAndWait();

        return answer;
    }
}
