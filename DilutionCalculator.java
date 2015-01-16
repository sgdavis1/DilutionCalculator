import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
 
/**
 * <p>Example dilution calculator for JavaFX, using Event Handling.
 * <p>Inspired by: http://javatutorialhq.com/javafx/form-creation/ but based on
 * a common microbiology calculation.
 *
 * @author sgdavis@bioneos.com
 */
public class DilutionCalculator extends Application 
{
  @Override
  public void start(Stage primaryStage) 
  {
    primaryStage.setTitle("JavaFX Dilution Calculator");
    GridPane pane = new GridPane();
    pane.setAlignment(javafx.geometry.Pos.CENTER);
    pane.setHgap(10);
    pane.setVgap(10);
    pane.setPadding(new javafx.geometry.Insets(25, 25, 25, 25));
    Scene scene = new Scene(pane, 300, 275);
 
    Text sceneTitle = new Text("Dilution Calculator");
    sceneTitle.setFont(Font.font("System", FontWeight.NORMAL,20));
    pane.add(sceneTitle, 0, 0, 2, 1);

    // Initial concentration
    Label initConcLbl = new Label("Initial (cells/mL):");
    initConcLbl.setFont(Font.font("System", FontWeight.NORMAL, 10));
    pane.add(initConcLbl, 0, 1);
    final TextField initConcField = new TextField();
    pane.add(initConcField, 1, 1);

    // Final concentration
    Label finalConcLbl = new Label("Final (cells/mL):");
    finalConcLbl.setFont(Font.font("System", FontWeight.NORMAL, 10));
    pane.add(finalConcLbl, 0, 2);
    final TextField finalConcField = new TextField();
    pane.add(finalConcField, 1, 2);
 
    // Final Volume
    Label finalVolumeLbl = new Label("Final Volume (mL):");
    finalVolumeLbl.setFont(Font.font("System", FontWeight.NORMAL, 10));
    pane.add(finalVolumeLbl, 0, 3);
    final TextField finalVolumeField = new TextField();
    pane.add(finalVolumeField, 1, 3);
    
    // Calculate button
    final Button calculate = new Button("Calculate!");
    HBox hbox = new HBox(10);
    hbox.setAlignment(javafx.geometry.Pos.BOTTOM_RIGHT);
    hbox.getChildren().add(calculate);
    pane.add(hbox, 1, 4);

    // Calculated values 
    final Label results = new Label("Calculated Volumes");
    results.setFont(Font.font("System", FontWeight.NORMAL, 14));
    pane.add(results, 0, 5, 2, 1);
    
    final Label resultBacteriaLbl = new Label("Volume Bacteria:");
    resultBacteriaLbl.setFont(Font.font("System", FontWeight.NORMAL, 10));
    pane.add(resultBacteriaLbl, 0, 6);
    final Label resultBacteria = new Label();
    pane.add(resultBacteria, 1, 6);
    
    final Label resultBrothLbl = new Label("Volume Broth:");
    resultBrothLbl.setFont(Font.font("System", FontWeight.NORMAL, 10));
    pane.add(resultBrothLbl, 0, 7);
    final Label resultBroth = new Label();
    pane.add(resultBroth, 1, 7);

    // Event handling
    calculate.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent t) 
      {
        Double initConc = Double.parseDouble(initConcField.getText());
        Double finalConc = Double.parseDouble(finalConcField.getText());
        Double finalVol = Double.parseDouble(finalVolumeField.getText());
        resultBacteria.setText(String.format("%.2f mL", finalVol * finalConc / initConc));
        resultBroth.setText(String.format("%.2f mL", finalVol - (finalVol * finalConc / initConc)));
      }
    });

    // Final setup
    primaryStage.setScene(scene);
    primaryStage.show();
  }
 
  /**
   * Fallback for command line launching.
   */
  public static void main(String[] args) 
  {
    launch(args);
  }
}
