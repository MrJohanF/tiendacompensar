module com.example.tiendacompensar {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tiendacompensar to javafx.fxml;
    exports com.example.tiendacompensar;
}