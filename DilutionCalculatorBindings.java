import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.util.converter.NumberStringConverter;
 
/**
 * <p>Example dilution calculator for JavaFX, using JavaFX Bindings.
 * <p>Inspired by: http://javatutorialhq.com/javafx/form-creation/ but based on
 * a common microbiology calculation.
 *
 * @author sgdavis@bioneos.com
 */
public class DilutionCalculatorBindings extends Application 
{
  private DoubleProperty initConc = new SimpleDoubleProperty(0);
  private DoubleProperty finalConc = new SimpleDoubleProperty(0);
  private DoubleProperty finalVolume = new SimpleDoubleProperty(0);
  
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
    Bindings.bindBidirectional(initConcField.textProperty(), initConc, new NumberStringConverter());
    pane.add(initConcField, 1, 1);

    // Final concentration
    Label finalConcLbl = new Label("Final (cells/mL):");
    finalConcLbl.setFont(Font.font("System", FontWeight.NORMAL, 10));
    pane.add(finalConcLbl, 0, 2);
    final TextField finalConcField = new TextField();
    Bindings.bindBidirectional(finalConcField.textProperty(), finalConc, new NumberStringConverter());
    pane.add(finalConcField, 1, 2);
 
    // Final Volume
    Label finalVolumeLbl = new Label("Final Volume (mL):");
    finalVolumeLbl.setFont(Font.font("System", FontWeight.NORMAL, 10));
    pane.add(finalVolumeLbl, 0, 3);
    final TextField finalVolumeField = new TextField();
    Bindings.bindBidirectional(finalVolumeField.textProperty(), finalVolume, new NumberStringConverter());
    pane.add(finalVolumeField, 1, 3);
    
    // Calculated values 
    final Label results = new Label("Calculated Volumes");
    results.setFont(Font.font("System", FontWeight.NORMAL, 14));
    pane.add(results, 0, 5, 2, 1);
    
    final Label resultBacteriaLbl = new Label("Volume Bacteria:");
    resultBacteriaLbl.setFont(Font.font("System", FontWeight.NORMAL, 10));
    pane.add(resultBacteriaLbl, 0, 6);
    final Label resultBacteria = new Label();
    resultBacteria.textProperty().bind(Bindings.format("%.2f mL", finalVolume.multiply(finalConc).divide(initConc)));
    pane.add(resultBacteria, 1, 6);
    
    final Label resultBrothLbl = new Label("Volume Broth:");
    resultBrothLbl.setFont(Font.font("System", FontWeight.NORMAL, 10));
    pane.add(resultBrothLbl, 0, 7);
    final Label resultBroth = new Label();
    resultBroth.textProperty().bind(Bindings.format("%.2f mL", finalVolume.subtract(finalVolume.multiply(finalConc).divide(initConc))));
    pane.add(resultBroth, 1, 7);


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
