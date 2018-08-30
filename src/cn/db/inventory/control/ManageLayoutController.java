package cn.db.inventory.control;

import cn.db.inventory.MainApp;
import cn.db.inventory.dao.*;
import cn.db.inventory.dao.impl.*;
import cn.db.inventory.model.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class ManageLayoutController {

    private MainLayoutController mainLayoutController;
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

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    public void setMainLayoutController(MainLayoutController mainLayoutController) {
        this.mainLayoutController = mainLayoutController;
    }

    /**
     * Called by user clicks the add button.
     * Opens a dialog to edit
     * add the record from databases and refresh the table.
     */
    @FXML
    private void handleAdd() {
        Modern modern = mainLayoutController.getModern();
        MainApp mainApp = mainLayoutController.getMainApp();
        switch (modern) {
            case GOODS:
                Goods goods = new Goods();
                mainLayoutController.getMainApp().showEditDialog(goods, modern);
                goodsDAO.add(goods);
                displayAllGoods();
                break;
            case RETRIEVAL:
                Retrieval retrieval = new Retrieval();
                mainApp.showEditDialog(retrieval, modern);
                retrievalDAO.add(retrieval);
//                Goods goods1 = goodsDAO.selectById(retrieval.getGoodsId()).get(0);
//                goods1.setInventory(goods1.getInventory() - retrieval.getNumber());
//                goodsDAO.update(goods1);
                displayAllRetrieval();
                break;
            case STORAGE:
                Storage storage = new Storage();
                mainApp.showEditDialog(storage, modern);
                storageDAO.add(storage);
                
//                Goods goods2 = goodsDAO.selectById(storage.getGoodsId()).get(0);
//                goods2.setInventory(goods2.getInventory() + storage.getNumber());
//                goodsDAO.update(goods2);
                displayAllStorage();
                break;
            case SUPPLIER:
                Supplier supplier = new Supplier();
                mainApp.showEditDialog(supplier, modern);
                supplierDAO.add(supplier);
                displayAllSupplier();
                break;
            case USER:
                User user = new User();
                mainApp.showEditDialog(user, modern);
                userDAO.add(user);
                displayAllUser();
                break;
        }
    }

    /**
     * Called by user clicks the delete button.
     * delete the record from databases and refresh the table.
     */
    @FXML
    private void handleDelete() {
        Modern modern = mainLayoutController.getModern();
        MainApp mainApp = mainLayoutController.getMainApp();
        Object o = tableView.getSelectionModel().getSelectedItem();
        if (o != null) {
            switch (modern) {
                case GOODS:
                    goodsDAO.delete((Goods) o);
                    displayAllGoods();
                    break;
                case RETRIEVAL:
                    retrievalDAO.delete((Retrieval) o);
                    displayAllRetrieval();
                    break;
                case STORAGE:
                    storageDAO.delete((Storage) o);
                    displayAllStorage();
                    break;
                case SUPPLIER:
                    supplierDAO.delete((Supplier) o);
                    displayAllSupplier();
                    break;
                case USER:
                    userDAO.delete((User) o);
                    displayAllUser();
                    break;
            }
        }
    }

    /**
     * Called by user clicks the update button.
     * open the dialog to edit
     * update the record from databases and refresh the table.
     */
    @FXML
    private void handleUpdate() {
        Modern modern = mainLayoutController.getModern();
        MainApp mainApp = mainLayoutController.getMainApp();
        Object o = tableView.getSelectionModel().getSelectedItem();
        if (o != null) {
            boolean okClicked = mainApp.showEditDialog(o, modern);
            if (okClicked) {
                switch (modern) {
                    case GOODS:
                        goodsDAO.update((Goods) o);
                        displayAllGoods();
                        break;
                    case RETRIEVAL:
                        retrievalDAO.update((Retrieval) o);
                        displayAllRetrieval();
                        break;
                    case STORAGE:
                        storageDAO.update((Storage) o);
                        displayAllStorage();
                        break;
                    case SUPPLIER:
                        supplierDAO.update((Supplier) o);
                        displayAllSupplier();
                        break;
                    case USER:
                        userDAO.update((User) o);
                        displayAllUser();
                        break;
                }
            }
        }
    }

    public void display() {
        switch (mainLayoutController.getModern()) {
            case GOODS:
                displayAllGoods();
                break;
            case SUPPLIER:
                displayAllSupplier();
                break;
            case STORAGE:
                displayAllStorage();
                break;
            case RETRIEVAL:
                displayAllRetrieval();
                break;
            case USER:
                displayAllUser();
                break;
        }
    }

    @FXML
    public void displayAllGoods() {
        c1Column.setCellValueFactory(new PropertyValueFactory<>("id"));
        c2Column.setCellValueFactory(new PropertyValueFactory<>("name"));
        c3Column.setCellValueFactory(new PropertyValueFactory<>("type"));
        c4Column.setCellValueFactory(new PropertyValueFactory<>("inventory"));
        c5Column.setCellValueFactory(new PropertyValueFactory<>("maxnum"));
        c6Column.setCellValueFactory(new PropertyValueFactory<>("minnum"));
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
    public void displayAllSupplier() {
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
    public void displayAllStorage() {
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
        tableView.setItems(FXCollections.observableArrayList(storageList));
        tableView.refresh();
    }

    @FXML
    public void displayAllRetrieval() {
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
     * Called when the user licks the userManager button.
     * display the data of user table on the right table view.
     */
    @FXML
    public void displayAllUser() {
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
