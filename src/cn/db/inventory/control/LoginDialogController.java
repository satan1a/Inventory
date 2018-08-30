package cn.db.inventory.control;
import cn.db.inventory.MainApp;
import cn.db.inventory.dao.impl.UserDAOimpl;
import cn.db.inventory.model.User;
import cn.db.inventory.until.DebugLog;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.VBox;

public class LoginDialogController {

    private boolean loginSuccess = false;
    @FXML
    private TextField userNameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label userNameErrorLabel;
    @FXML
    private Label passwordErrorLabel;

    @FXML
    private Button loginButton;
    @FXML
    private Button resetButton;
    @FXML
    private Label loginErrorLabel;

    @FXML
    private VBox rootVBox;

    // Reference to the main application.
    private MainApp mainApp;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        loginButton.setEffect(new Reflection());
        resetButton.setEffect(new Reflection());
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *q
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    private boolean isInputEmpty() {
        StringBuffer errorMessage = new StringBuffer();
        if (userNameField.getText().equals("")) {
            errorMessage.append("UserName is empty\t");
            userNameErrorLabel.setVisible(true);
        } else {
            userNameErrorLabel.setVisible(false);
        }
        if (passwordField.getText().equals("")) {
            errorMessage.append(", Password is empty\t");
            passwordErrorLabel.setVisible(true);
        } else {
            passwordErrorLabel.setVisible(false);
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            DebugLog.Log("Login Message: " + errorMessage.toString());
            return false;
        }
    }

    /**
     * Check the user input userName and password
     *
     * @return true if the username and password all is right
     */
    private boolean checkPassword(String username, String password) {
        UserDAOimpl userDAOimpl = new UserDAOimpl();
        User user = userDAOimpl.CheckUser(username, password);
        if ( user != null) {
            mainApp.setUser(user);
            return true;
        }
        return false;
//        return true;
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String userName = userNameField.getText();
        String userPassword = passwordField.getText();
        DebugLog.Log("Login: username: " + userName + " password:" + userPassword);
        loginSuccess = checkPassword(userName, userPassword);
        if (loginSuccess) {
            loginErrorLabel.setVisible(false);
            return true;
        } else {
            loginErrorLabel.setVisible(true);
            DebugLog.Log("Login: username or password error!");
            return false;
        }
    }

    @FXML
    public void handleLogin() {
        if (isInputEmpty() && isInputValid()) {
            mainApp.showMainLayout();
        }
    }

    @FXML
    public void handleReset() {
        userNameField.setText("");
        userNameErrorLabel.setVisible(false);
        passwordField.setText("");
        passwordErrorLabel.setVisible(false);
        loginErrorLabel.setVisible(false);
        userNameField.requestFocus();
    }

}
