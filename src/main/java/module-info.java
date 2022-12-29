module com.example.filecompressoranddecompressor {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.filecompressoranddecompressor to javafx.fxml;
    exports com.example.filecompressoranddecompressor;
}