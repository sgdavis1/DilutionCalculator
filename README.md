# DilutionCalculator
*A comparison of JavaFX using EventHandling versus Binding*

Similar to my [JavaFX Binding Example]:(https://github.com/sgdavis1/JavaFxBindingEx), this
repository serves to demonstrate using JavaFX Binding in a simple JavaFX Application. For this
example, I have removed all of the MVC organization and project files and stripped it down to two
Java source code files. The entire Application is defined in both of these files, with the simple
difference of using JavaFX Bindings in one, and Event handling in another.

The example is a very simple common molecular biology calculation, the dilution of a sample. Both
perform the same calculations, but you can compile and execute the applications to see the
difference in user interface / experience.

The main code difference can be summarized with this:

**Event Handling**

```java
  final Button calculate = new Button("Calculate!");
...
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
```

**Binding**

```java
  private DoubleProperty initConc = new SimpleDoubleProperty(0);
  private DoubleProperty finalConc = new SimpleDoubleProperty(0);
  private DoubleProperty finalVolume = new SimpleDoubleProperty(0);
...
  Bindings.bindBidirectional(initConcField.textProperty(), initConc, new NumberStringConverter());
...
  Bindings.bindBidirectional(finalConcField.textProperty(), finalConc, new NumberStringConverter());
...
  Bindings.bindBidirectional(finalVolumeField.textProperty(), finalVolume, new NumberStringConverter());
...
  final Label resultBacteria = new Label();
  resultBacteria.textProperty().bind(Bindings.format("%.2f mL", finalVolume.multiply(finalConc).divide(initConc)));
  final Label resultBroth = new Label();
  resultBroth.textProperty().bind(Bindings.format("%.2f mL", finalVolume.subtract(finalVolume.multiply(finalConc).divide(initConc))));
```

The advantage of the Bindings example really isn't in the code itself (concise and easy to follow 
, but it isn't better or worse than the Event Handling, IMO). The advantage really is in the UX,
what Bindings allows is for a consistent and immediately up to date user interface. In addition, by
adding bound Properties defined as class variables, you now have a **single** source for the proper
value of a variable at all times (in the UI or in the code). This alone is the reason to switch, as
it will remove a large amount of boilerplate in creating new listeners, events and the resulting
logic to enable this level of immediate interaction. After all, these days responsiveness of your
applications is paramount, and you really don't want your users to have to spend their time with
that extra "Calculate" button click ;)
