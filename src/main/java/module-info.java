module org.hugo.dein.jasperejercicio1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires net.sf.jasperreports.core;


    opens org.hugo.dein.jasperejercicio1 to javafx.fxml;
    exports org.hugo.dein.jasperejercicio1;
}