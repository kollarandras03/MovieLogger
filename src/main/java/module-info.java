module view.movielogger {
    requires javafx.controls;
    requires javafx.fxml;


    opens view to javafx.fxml;
    exports view;
}