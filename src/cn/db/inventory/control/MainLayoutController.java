package cn.db.inventory.control;

import cn.db.inventory.MainApp;
import cn.db.inventory.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * 【控制器】
 *  功能：读取view层里的fxml，加载主页面
 */
public class MainLayoutController {
    // Reference to the main application.
    private User user;
    private MainApp mainApp;
    private Modern modern;
    private ViewModern viewModern;

    private ManageLayoutController manageLayoutController;
    private CriteriaQueryLayoutController criteriaQueryLayoutController;

    @FXML
    private TitledPane sysManageTpane;
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab tabQuery;
    @FXML
    private Tab tabManage;
    @FXML
    private BorderPane borderPaneManage;
    @FXML
    private BorderPane borderPaneQuery;
    @FXML
    private BorderPane borderPaneChart;
    @FXML
    private Label infoTitleLabel;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        modern = Modern.GOODS;
        viewModern = ViewModern.CRITERIAQUERYLAYOUT;
        showCriteriaQueryLayout();


    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        user = mainApp.getUser();
        if (!user.getRole().equals("管理员")) {
            tabManage.setDisable(true);
            sysManageTpane.setVisible(false);
        }
    }

    public MainApp getMainApp() {
        return mainApp;
    }

    public Modern getModern() {
        return modern;
    }

    @FXML
    public void showManageLayout() {
        viewModern = ViewModern.MANAGELAYOUT;
        try {

            // Load StudentLayout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/ManageLayout.fxml"));
            VBox manageLayout = loader.load();
            borderPaneManage.setCenter(manageLayout);
            manageLayoutController = loader.getController();
            manageLayoutController.setMainLayoutController(this);
            manageLayoutController.display();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void showCriteriaQueryLayout() {
        viewModern = ViewModern.CRITERIAQUERYLAYOUT;
        try {
            // Load StudentLayout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/CriteriaQueryLayout.fxml"));
            VBox criteriaQueryLayout = loader.load();
            borderPaneQuery.setCenter(criteriaQueryLayout);
            criteriaQueryLayoutController = loader.getController();
            criteriaQueryLayoutController.setMainLayoutController(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void showView() {
        switch (viewModern) {
            case MANAGELAYOUT:
                manageLayoutController.display();
                break;
            case CRITERIAQUERYLAYOUT:
                criteriaQueryLayoutController.display();
                break;
        }
    }

    @FXML
    public void displayGoods() {
        modern = Modern.GOODS;
        infoTitleLabel.setText("库存信息");
        showView();
    }

    @FXML
    public void displaySupplier() {
        modern = Modern.SUPPLIER;
        infoTitleLabel.setText("供应商信息");
        showView();
    }

    @FXML
    public void displayStorage() {
        if (user.getRole().equals("进货员")) tabManage.setDisable(false);
        if (user.getRole().equals("出货员")) {
            tabManage.setDisable(true);
            tabPane.getSelectionModel().select(tabQuery);
        }
        modern = Modern.STORAGE;
        infoTitleLabel.setText("入库管理");
        showView();
    }

    @FXML
    public void displayRetrieval() {
        if (user.getRole().equals("出货员")) tabManage.setDisable(false);
        if (user.getRole().equals("进货员")) {
            tabManage.setDisable(true);
            tabPane.getSelectionModel().select(tabQuery);
        }
        modern = Modern.RETRIEVAL;
        infoTitleLabel.setText("出库管理");
        showView();
    }

    /**
     * Called when the user licks the userManager button.
     * display the data of user table on the right table view.
     */
    @FXML
    public void displayUser() {
        modern = Modern.USER;
        infoTitleLabel.setText("用户管理");
        showView();
    }

    /**
     * Called when the user clicks the button. Back to the login dialog.
     */
    @FXML
    public void handleSwitch() {
        mainApp.showLoginDialog();
    }

    /**
     * exit the application
     */
    @FXML
    public void handleExit() {
        System.exit(0);
    }
}
