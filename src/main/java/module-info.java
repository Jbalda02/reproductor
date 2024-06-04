module com.proyectodatos {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    opens com.proyectodatos to javafx.fxml;
    exports com.proyectodatos;
}
