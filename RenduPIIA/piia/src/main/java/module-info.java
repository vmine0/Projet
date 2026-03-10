module com.example.piia {
    requires javafx.controls;
    requires javafx.fxml;
    requires io.github.javadiffutils;


    opens com.example.piia to javafx.fxml;
    exports com.example.piia;
}