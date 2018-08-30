package cn.db.inventory.control;

import cn.db.inventory.dao.*;
import cn.db.inventory.dao.impl.*;
import cn.db.inventory.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Date;
import java.util.List;

public class CriteriaQueryLayoutController {
    private MainLayoutController mainLayoutController;
    @FXML
    private ComboBox comboBox;
    ObservableList<String> optionGoods = FXCollections.observableArrayList("产品编号", "产品名称", "类型");
    ObservableList<String> optionRetrieval = FXCollections.observableArrayList("出货订单号", "产品编号", "日期");
    ObservableList<String> optionStorage = FXCollections.observableArrayList("进货订单号", "产品编号", "供应商编号", "操作员");
    ObservableList<String> optionSupplier = FXCollections.observableArrayList("供应商编号", "厂商名", "联系人");
    ObservableList<String> optionUser = FXCollections.observableArrayList("用户编号", "用户名", "角色类型");
    @FXML
    private TextField textField;	
    @FXML
    private Button buttonQuery;
    @FXML
    private TableView<Object> tableView;
    @FXML
    private TableColumn<Object, String> c1Column;
    @FXML
    private TableColumn<Object, String> c2Column;
    @FXML
    private TableColumn<Object, String> c3Column;
    @FXML
    private TableColumn<Object, String> c4Column;
    @FXML
    private TableColumn<Object, String> c5Column;
    @FXML
    private TableColumn<Object, String> c6Column;
    @FXML
    private TableColumn<Object, String> c7Column;


    GoodsDAO goodsDAO = new GoodsDAOimpl();
    SupplierDAO supplierDAO = new SupplierDAOimpl();
    StorageDAO storageDAO = new StorageDAOimpl();
    RetrievalDAO retrievalDAO = new RetrievalDAOimpl();
    UserDAO userDAO = new UserDAOimpl();
    List result;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        displayGoods();
        comboBox.getSelectionModel().select(0);
    }

    public void setMainLayoutController(MainLayoutController mainLayoutController) {
        this.mainLayoutController = mainLayoutController;
    }

    @FXML
    public void handleQuery() {
        Modern modern = mainLayoutController.getModern();
        String conditionName = (String) comboBox.getSelectionModel().getSelectedItem();
        String condition = textField.getText();
        switch (modern) {
            case GOODS:
                switch (conditionName) {
                    case "产品编号": result = goodsDAO.selectById(condition);
                        break;
                    case "产品名称": result = goodsDAO.selectByName(condition);
                        break;
                    case "类型": result = goodsDAO.selectByType(Integer.valueOf(condition));
                        break;
                }
                break;
            case SUPPLIER:
                switch (conditionName) {
                    case "供应商编号": result = supplierDAO.selectById(condition);
                        break;
                    case "厂商名": result = supplierDAO.selectByName(condition);
                        break;
                    case "联系人": result = supplierDAO.selectByContactMan(condition);
                        break;
                }
                break;
            case STORAGE:
                switch (conditionName) {
                    case "进货订单号": result = storageDAO.selectById(condition);
                        break;
                    case "产品编号": result = storageDAO.selectByGoodsId(condition);
                        break;
                    case "供应商编号": result = storageDAO.selectBySupplierId(condition);
                        break;
                    case "操作员": result = storageDAO.selectByOperator(condition);
                }
                break;
            case RETRIEVAL:
                switch (conditionName) {
                    case "出货订单号": result = retrievalDAO.selectById(condition);
                        break;
                    case "产品编号": result = retrievalDAO.selectByGoodsId(condition);
                        break;
                    case "日期": result = retrievalDAO.selectByDateAfter(Date.valueOf(condition));
                        break;
                }
                break;
            case USER:
                switch (conditionName) {
                    case "用户编号": result = userDAO.selectById(condition);
                        break;
                    case "用户名": result = userDAO.selectByName(condition);
                        break;
                    case "角色类型": result = userDAO.selectByRole(condition);
                        break;
                }
                break;
        }
        tableView.setItems(FXCollections.observableArrayList(result));
        tableView.refresh();
    }

    public void display() {
        Modern modern = mainLayoutController.getModern();
        switch (modern) {
            case GOODS:
                displayGoods();
                break;
            case SUPPLIER:
                displaySupplier();
                break;
            case STORAGE:
                displayStorage();
                break;
            case RETRIEVAL:
                displayRetrieval();
                
                break;
            case USER:
                displayUser();
                break;
        }
        comboBox.getSelectionModel().select(0);

    }

    @FXML
    public void displayGoods() {
        comboBox.setItems(optionGoods);
        c1Column.setCellValueFactory(new PropertyValueFactory<>("id"));
        c2Column.setCellValueFactory(new PropertyValueFactory<>("name"));
        c3Column.setCellValueFactory(new PropertyValueFactory<>("type"));
        c4Column.setCellValueFactory(new PropertyValueFactory<>("inventory"));
        c5Column.setCellValueFactory(new PropertyValueFactory<>("maxnum"));
        c6Column.setCellValueFactory(new PropertyValueFactory<>("minnum"));
        c7Column.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        c1Column.setText("产品编号");
        c2Column.setText("产品名称");
        c3Column.setText("类别");
        c4Column.setText("库存");
        c5Column.setText("库存上限");
        c6Column.setText("库存下限");
        c7Column.setText("供应商编号");
        c5Column.setVisible(true);
        c6Column.setVisible(true);
        c7Column.setVisible(false);
        List<Goods> goodsList = goodsDAO.selectAll();
        tableView.setItems(FXCollections.observableArrayList(goodsList));
        tableView.refresh();
    }

    @FXML
    public void displaySupplier() {
        comboBox.setItems(optionSupplier);
        c1Column.setCellValueFactory(new PropertyValueFactory<>("id"));
        c2Column.setCellValueFactory(new PropertyValueFactory<>("name"));
        c3Column.setCellValueFactory(new PropertyValueFactory<>("address"));
        c4Column.setCellValueFactory(new PropertyValueFactory<>("phone"));
        c5Column.setCellValueFactory(new PropertyValueFactory<>("contactMan"));
        c6Column.setCellValueFactory(new PropertyValueFactory<>("email"));
        c1Column.setText("供应商编号");
        c2Column.setText("厂商名");
        c3Column.setText("地址");
        c4Column.setText("电话");
        c5Column.setText("联系人");
        c6Column.setText("邮箱");
        c5Column.setVisible(true);
        c6Column.setVisible(true);
        c7Column.setVisible(false);
        List<Supplier> supplierList = supplierDAO.selectAll();
        tableView.setItems(FXCollections.observableArrayList(supplierList));
        tableView.refresh();
    }

    @FXML
    public void displayStorage() {
        comboBox.setItems(optionStorage);
        c1Column.setCellValueFactory(new PropertyValueFactory<>("id"));
        c2Column.setCellValueFactory(new PropertyValueFactory<>("goodsId"));
        c5Column.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        c4Column.setCellValueFactory(new PropertyValueFactory<>("number"));
        c3Column.setCellValueFactory(new PropertyValueFactory<>("name"));
        c6Column.setCellValueFactory(new PropertyValueFactory<>("data"));
        c7Column.setCellValueFactory(new PropertyValueFactory<>("operator"));
        c1Column.setText("进货订单号");
        c2Column.setText("商品编号");
        c5Column.setText("供应商编号");
        c4Column.setText("数量");
        c3Column.setText("商品名称");
        c6Column.setText("日期");
        c7Column.setText("操作员");
        c5Column.setVisible(true);
        c6Column.setVisible(true);
        c7Column.setVisible(true);
        List<Storage> storageList = storageDAO.selectAll();
        tableView. setItems(FXCollections.observableArrayList(storageList));
        tableView.refresh();
    }

    @FXML
    public void displayRetrieval() {
        comboBox.setItems(optionRetrieval);
        c1Column.setCellValueFactory(new PropertyValueFactory<>("id"));
        c2Column.setCellValueFactory(new PropertyValueFactory<>("goodsId"));
        c3Column.setCellValueFactory(new PropertyValueFactory<>("date"));
        c4Column.setCellValueFactory(new PropertyValueFactory<>("outPrice"));
        c5Column.setCellValueFactory(new PropertyValueFactory<>("number"));
        c6Column.setCellValueFactory(new PropertyValueFactory<>("consignee"));
        c7Column.setCellValueFactory(new PropertyValueFactory<>("operator"));
        c1Column.setText("出货订单号");
        c2Column.setText("商品编号");
        c3Column.setText("日期");
        c4Column.setText("出货价格");
        c5Column.setText("数量");
        c6Column.setText("收货人");
        c7Column.setText("操作员");
        c5Column.setVisible(true);
        c6Column.setVisible(true);
        c7Column.setVisible(true);
        List<Retrieval> retrievalList = retrievalDAO.selectAll();
        tableView.setItems(FXCollections.observableArrayList(retrievalList));
        tableView.refresh();
    }

    /**
     * Called when the user licks the 用户管理 button.
     * display the data of user table on the right table view.
     */
    @FXML
    public void displayUser() {
        comboBox.setItems(optionUser);
        c1Column.setCellValueFactory(new PropertyValueFactory<>("id"));
        c2Column.setCellValueFactory(new PropertyValueFactory<>("username"));
        c3Column.setCellValueFactory(new PropertyValueFactory<>("password"));
        c4Column.setCellValueFactory(new PropertyValueFactory<>("role"));
        c1Column.setText("用户编号");
        c2Column.setText("用户名");
        c3Column.setText("密码");
        c4Column.setText("角色");
        c5Column.setVisible(false);
        c6Column.setVisible(false);
        c7Column.setVisible(false);
        List<User> userList = userDAO.selectAll();
        tableView.setItems(FXCollections.observableArrayList(userList));
        tableView.refresh();
    }
}
