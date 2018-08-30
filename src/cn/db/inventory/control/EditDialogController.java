package cn.db.inventory.control;


import cn.db.inventory.model.*;
import cn.db.inventory.until.DebugLog;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Date;

/**
 * 【控制器】
 *  功能：修改记录
 *  对应：记录管理 选项中的修改选项
 */
public class EditDialogController {

    private boolean okClicked = false;
    private Stage dialogStage;
    Object o;
    Modern modern;

    @FXML
    private TextField t1Field;
    @FXML
    private TextField t2Field;
    @FXML
    private TextField t3Field;
    @FXML
    private TextField t4Field;
    @FXML
    private TextField t5Field;
    @FXML
    private TextField t6Field;
    @FXML
    private TextField t7Field;
    @FXML
    private TextField t8Field;
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Label label3;
    @FXML
    private Label label4;
    @FXML
    private Label label5;
    @FXML
    private Label label6;
    @FXML
    private Label label7;
    @FXML
    private Label label8;

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setObject(Object o, Modern modern) {
        this.o = o;
        this.modern = modern;
        switch (modern) {
            case GOODS:
                label1.setText("商品编号");
                label2.setText("产品名称");
                label3.setText("类别");
                label4.setText("库存：");
                label5.setText("库存上限");
                label6.setText("库存下限");
                label7.setVisible(false);
                t1Field.setText(((Goods) o).getId());
                t2Field.setText(((Goods) o).getName());
                t3Field.setText(String.valueOf(((Goods) o).getType()));
                t4Field.setText(String.valueOf(((Goods) o).getInventory()));
                t5Field.setText(String.valueOf(((Goods) o).getMaxnum()));
                t6Field.setText(String.valueOf(((Goods) o).getMinnum()));
                t7Field.setVisible(false);
                break;
            case RETRIEVAL:
                label1.setText("出货订单号");
                label2.setText("商品编号");
                label3.setText("日期");
                label4.setText("出货价格");
                label5.setText("数量");
                label6.setText("收货人");
                label7.setText("操作员");
                t1Field.setText(((Retrieval) o).getId());
                t2Field.setText(((Retrieval) o).getGoodsId());
                t3Field.setText(String.valueOf(((Retrieval) o).getDate()));
                t4Field.setText(String.valueOf(((Retrieval) o).getOutPrice()));
                t5Field.setText(String.valueOf(((Retrieval) o).getNumber()));
                t6Field.setText(((Retrieval) o).getConsignee());
                t7Field.setText(((Retrieval) o).getOperator());
                t7Field.setVisible(true);
                break;
            case STORAGE:
                label1.setText("进货订单号");
                label2.setText("商品编号");
                label3.setText("供应商编号");
                label4.setText("数量");
                label5.setText("类型");
                label6.setText("日期");
                label7.setText("操作员");
                label8.setText("商品名");
                t1Field.setText(((Storage) o).getId());
                t2Field.setText(((Storage) o).getGoodsId());
                t3Field.setText(((Storage) o).getSupplierId());
                t4Field.setText(String.valueOf(((Storage) o).getNumber()));
                t5Field.setText(String.valueOf(((Storage) o).getType()));
                t6Field.setText(String.valueOf(((Storage) o).getData()));
                t7Field.setText(((Storage) o).getOperator());
                t7Field.setVisible(true);
                t8Field.setText(((Storage) o).getName());
                t8Field.setVisible(true);
                break;
            case SUPPLIER:
                label1.setText("供应商编号");
                label2.setText("厂商");
                label3.setText("地址");
                label4.setText("电话");
                label5.setText("联系人");
                label6.setText("邮箱");
                label7.setVisible(false);
                t1Field.setText(((Supplier) o).getId());
                t2Field.setText(((Supplier) o).getName());
                t3Field.setText(((Supplier) o).getAddress());
                t4Field.setText(((Supplier) o).getPhone());
                t5Field.setText(((Supplier) o).getContactMan());
                t6Field.setText(((Supplier) o).getEmail());
                t7Field.setVisible(false);
                break;
            case USER:
                label1.setText("用户编号");
                label2.setText("用户名");
                label3.setText("密码");
                label4.setText("角色类型");
                label5.setVisible(false);
                label6.setVisible(false);
                label7.setVisible(false);
                t1Field.setText(((User) o).getId());
                t2Field.setText(((User) o).getUsername());
                t3Field.setText(((User) o).getPassword());
                t4Field.setText(((User) o).getRole());
                t5Field.setVisible(false);
                t6Field.setVisible(false);
                t7Field.setVisible(false);
                break;
        }
        filterTextFieldNull();
    }

    public void filterTextFieldNull() {
        if (t1Field.getText() == null || t1Field.getText().equals("")) {
            t2Field.setText("");
            t3Field.setText("");
            t4Field.setText("");
            t5Field.setText("");
            t6Field.setText("");
            t7Field.setText("");
        }
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            switch (modern) {
                case GOODS:
                    ((Goods) o).setId(t1Field.getText());
                    ((Goods) o).setName(t2Field.getText());
                    ((Goods) o).setType(Integer.valueOf(t3Field.getText()));
                    ((Goods) o).setInventory((Integer.valueOf(t4Field.getText())));
                    ((Goods) o).setMaxnum(Integer.valueOf(t5Field.getText()));
                    ((Goods) o).setMinnum(Integer.valueOf(t6Field.getText()));

                    break;
                case RETRIEVAL:
                    ((Retrieval) o).setId(t1Field.getText());
                    ((Retrieval) o).setGoodsId(t2Field.getText());
                    ((Retrieval) o).setDate(Date.valueOf(t3Field.getText()));
                    ((Retrieval) o).setOutPrice(Double.valueOf(t4Field.getText()));
                    ((Retrieval) o).setNumber(Integer.valueOf(t5Field.getText()));
                    ((Retrieval) o).setConsignee(t6Field.getText());
                    ((Retrieval) o).setOperator(t7Field.getText());
                    break;
                case STORAGE:
                    ((Storage) o).setId(t1Field.getText());
                    ((Storage) o).setGoodsId(t2Field.getText());
                    ((Storage) o).setSupplierId(t3Field.getText());
                    ((Storage) o).setNumber(Integer.valueOf(t4Field.getText()));
                    ((Storage) o).setType(Integer.valueOf(t5Field.getText()));
                    ((Storage) o).setData(Date.valueOf(t6Field.getText()));
                    ((Storage) o).setOperator(t7Field.getText());
                    ((Storage) o).setName(t8Field.getText());
                    break;
                case SUPPLIER:
                    ((Supplier) o).setId(t1Field.getText());
                    ((Supplier) o).setName(t2Field.getText());
                    ((Supplier) o).setAddress(t3Field.getText());
                    ((Supplier) o).setPhone(t4Field.getText());
                    ((Supplier) o).setContactMan(t5Field.getText());
                    ((Supplier) o).setEmail(t6Field.getText());
                    break;
                case USER:
                    ((User) o).setId(t1Field.getText());
                    ((User) o).setUsername(t2Field.getText());
                    ((User) o).setPassword(t3Field.getText());
                    ((User) o).setRole(t4Field.getText());
                    break;
            }
            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (t1Field.getText() == null || t1Field.getText().length() == 0) {
            errorMessage += "No valid t1Field!\n";
        }
        if (t2Field.getText() == null || t2Field.getText().length() == 0) {
            errorMessage += "No valid t2Field!\n";
        }
        if (t3Field.getText() == null || t3Field.getText().length() == 0) {
            errorMessage += "No valid t3Field!\n";
        }
        if (modern != Modern.GOODS) {
            if (t4Field.getText() == null || t4Field.getText().length() == 0) {
                errorMessage += "No valid t4Field!\n";
            }
        }
        if (modern != Modern.USER) {
            if (t5Field.getText() == null || t5Field.getText().length() == 0) {
                errorMessage += "No valid t5Field!\n";
            }
            if (t6Field.getText() == null || t6Field.getText().length() == 0) {
                errorMessage += "No valid t6Field!\n";
            }
        }
        if (modern != Modern.SUPPLIER && modern != Modern.USER && modern != Modern.GOODS) {
            if (t7Field.getText() == null || t7Field.getText().length() == 0) {
                errorMessage += "No valid t7Field!\n";
            }
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            DebugLog.Log("EditDialog: " + errorMessage);
            return false;
        }
    }
}
