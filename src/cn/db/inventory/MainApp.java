package cn.db.inventory;

import java.io.IOException;

import cn.db.inventory.control.Modern;
import cn.db.inventory.control.EditDialogController;
import cn.db.inventory.control.LoginDialogController;
import cn.db.inventory.control.MainLayoutController;
import cn.db.inventory.model.User;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.fxml.FXMLLoader;

public class MainApp extends Application {

	private Stage primaryStage;
    private VBox loginDialog;
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			this.primaryStage = primaryStage;
			this.primaryStage.setTitle("仓储管理系统");
			// Set the application icon.
			this.primaryStage.getIcons().add(
	                new Image("file:src/cn/db/inventory/resources/images/cat.jpg"));
			showLoginDialog();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void showLoginDialog() {
		try {
			FXMLLoader loader = new FXMLLoader();
			// Load login Dialog from fxml file.
			loader.setLocation(MainApp.class.getResource("view/LoginDialog.fxml"));
			loginDialog = loader.load();
			fitScreen(loginDialog);
			Scene scene = new Scene(loginDialog);
			primaryStage.setScene(scene);
			// Give the controller access to the main app.
			LoginDialogController controller = loader.getController();
			controller.setMainApp(this);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showMainLayout() {
		try {
			
			FXMLLoader loader = new FXMLLoader();
			// Load StudentLayout from fxml file.
			loader.setLocation(MainApp.class.getResource("view/MainLayout.fxml"));
			SplitPane studentLayout = loader.load();
			fitScreen(studentLayout);
			Scene scene = new Scene(studentLayout);
			primaryStage.setScene(scene);
			// Give the controller access to the main app.
			MainLayoutController controller = loader.getController();
			controller.setMainApp(this);
			primaryStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean showEditDialog(Object o, Modern modern) {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/EditDialog.fxml"));
			AnchorPane page = loader.load();
			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("添加");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			dialogStage.getIcons().add(
					new Image("file:src/cn/db/inventory/resources/images/guet.png"));
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			// Set the person into the controller.
			EditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setObject(o, modern);
			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();
			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	private void fitScreen(Region pane) {
		Screen screen = Screen.getPrimary();
		Rectangle2D bounds = screen.getVisualBounds();
		double width = pane.getPrefWidth();
		double height = pane.getPrefHeight();
		primaryStage.setHeight(height);
		primaryStage.setWidth(width);
		primaryStage.setX((bounds.getWidth() - width)/2);
		primaryStage.setY((bounds.getHeight() - height)/2);
	}
}
