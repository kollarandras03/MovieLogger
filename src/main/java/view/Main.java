package view;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class Main extends Application {

    private List<String> categories = new ArrayList<>();
    private List<String> actors = new ArrayList<>();
    private List<String> movies = new ArrayList<>();
    private HBox categoryList = new HBox(5);
    private HBox list2Container = new HBox(5);
    private VBox dummyEntriesBox = new VBox(10);

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("MovieLogger");

        // MovieLogger title
        Label title = new Label("MovieLogger");
        title.setFont(new Font("Arial", 24));
        title.setStyle("-fx-font-weight: bold;");
        HBox titleRow = new HBox(10, title);
        titleRow.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(title, Priority.ALWAYS);

        // First row components
        TextField nameField = new TextField();
        nameField.setPromptText("Name");
        Button searchButton = new Button("S");
        searchButton.getStyleClass().add("button-12");
        Button plusButton = new Button("+");

        HBox firstRow = new HBox(10, nameField, searchButton, plusButton);
        firstRow.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(nameField, Priority.ALWAYS);
        searchButton.setMaxWidth(50);
        plusButton.setMaxWidth(50);

        // Second row components
        TextField categoryTextField = new TextField();
        categoryTextField.setPromptText("Enter category");
        Button categoryButton = new Button("Add");
        categoryList.setAlignment(Pos.CENTER_LEFT);
        HBox list1Row = new HBox(10, categoryTextField, categoryButton, categoryList);
        list1Row.setAlignment(Pos.CENTER_LEFT);

        // Third row components
        TextField list2TextField = new TextField();
        list2TextField.setPromptText("Add to List 2");
        Button addButton2 = new Button("Add");
        list2Container.setAlignment(Pos.CENTER_LEFT);
        HBox list2Row = new HBox(10, list2TextField, addButton2, list2Container);
        list2Row.setAlignment(Pos.CENTER_LEFT);

        // Fourth row components
        TextArea largeTextArea = new TextArea();
        largeTextArea.setPromptText("Enter larger text here...");
        largeTextArea.setPrefRowCount(2);
        largeTextArea.setPrefColumnCount(30);

        // VBox for the top section
        VBox mainLayout = new VBox(10, titleRow, firstRow, list1Row, list2Row, largeTextArea, dummyEntriesBox);
        mainLayout.setAlignment(Pos.TOP_LEFT);
        mainLayout.setPadding(new Insets(10));

        nameField.styleProperty().bind(
                Bindings
                        .when(nameField.focusedProperty())
                        .then("-fx-prompt-text-fill: derive(-fx-control-inner-background, -30%);")
                        .otherwise("-fx-prompt-text-fill: derive(-fx-control-inner-background, -30%);"));



        // Create the scene
        Scene scene = new Scene(mainLayout, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
        firstRow.requestFocus();
        URL r = this.getClass().getResource("style.css");
        if(r != null){
            scene.getStylesheets().add(r.toString());
        }
        else{
            System.out.println("Could not load css file!");
        }


        // Add button actions
        categoryButton.setOnAction(e -> addToList(categoryTextField.getText(), categories, categoryList));
        addButton2.setOnAction(e -> addToList(list2TextField.getText(), actors, list2Container));

        // Dummy data for demonstration
        movies.add("Dummy Entry 1");
        movies.add("Dummy Entry 2");
        movies.add("Dummy Entry 3");
        movies.add("Dummy Entry 4");
        movies.add("Dummy Entry 5");
        movies.add("Dummy Entry 6");

        searchButton.setOnAction(e -> {
            dummyEntriesBox.getChildren().clear();
            for (String entry : movies) {
                Label dummyLabel = new Label(entry);
                dummyEntriesBox.getChildren().add(dummyLabel);
            }
        });
    }


    private void addToList(String text, List<String> list, HBox container) {
        if (text.isEmpty() || list.size() >= 3 || list.contains(text)) return;

        list.add(text);
        HBox itemBox = createListItem(text, list, container);
        container.getChildren().add(itemBox);
    }

    private HBox createListItem(String text, List<String> list, HBox container) {
        Label label = new Label(text);
        Button deleteButton = new Button("x");
        HBox itemBox = new HBox(5, label, deleteButton);
        itemBox.setAlignment(Pos.CENTER_LEFT);

        deleteButton.setOnAction(e -> {
            list.remove(text);
            container.getChildren().remove(itemBox);
        });

        return itemBox;
    }

    public static void main(String[] args) {
        launch(args);
    }
}






/*
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}*/