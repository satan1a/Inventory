package cn.db.inventory.until;

import javafx.scene.control.Alert;

public class AlertUtil {
    public static void showAlterDialog(String title,String text){
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }
}
