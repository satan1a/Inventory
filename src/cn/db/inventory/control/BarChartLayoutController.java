package cn.db.inventory.control;

import cn.db.inventory.dao.GoodsDAO;
import cn.db.inventory.dao.GoodsTypeDAO;
import cn.db.inventory.dao.impl.*;
import cn.db.inventory.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BarChartLayoutController {

    private MainLayoutController mainLayoutController;
    @FXML
    private CategoryAxis xAxis1;
    @FXML
    private NumberAxis yAxis1;
    @FXML
    private BarChart<String, Integer> bc1;

    @FXML
    private CategoryAxis xAxis2;
    @FXML
    private NumberAxis yAxis2;
    @FXML
    private BarChart<String, Integer> bc2;

    ObservableList<XYChart.Series<String, Integer>> barChartData1 =
            FXCollections.observableArrayList();
    ObservableList<XYChart.Series<String, Integer>> barChartData2 =
            FXCollections.observableArrayList();

    GoodsDAO goodsDAO = new GoodsDAOimpl();
    GoodsTypeDAO goodsTypeDAO = new GoodsTypeDAOimpl();
    SupplierDAOimpl supplierDAOimpl = new SupplierDAOimpl();
    StorageDAOimpl storageDAOimpl = new StorageDAOimpl();
    RetrievalDAOimpl retrievalDAOimpl = new RetrievalDAOimpl();
    UserDAOimpl userDAOimpl = new UserDAOimpl();


    public void setMainLayoutController(MainLayoutController mainLayoutController) {
        this.mainLayoutController = mainLayoutController;
    }

    public void display() {
        Modern modern = mainLayoutController.getModern();
        switch (modern) {
            case GOODS: displayGoodsChart();
                break;
            case SUPPLIER: displaySupplierChart();
                break;
            case STORAGE: displayStorageChart();
                break;
            case RETRIEVAL: displayRetrievalChart();
                break;
            case USER: displayUserChart();
                break;
        }
    }

    public void displayGoodsChart(){
        barChartData1.clear();
        bc1.setTitle("产品信息条形图");
        xAxis1.setLabel("产品名称");
        yAxis1.setLabel("数量");
        List<Goods> goodsList = goodsDAO.selectAll();
        XYChart.Series<String, Integer> series1 = new XYChart.Series();
        XYChart.Series<String, Integer> series2 = new XYChart.Series();
        XYChart.Series<String, Integer> series3 = new XYChart.Series();
        series1.setName("货存量");
        series2.setName("存货上限");
        series3.setName("存货下限");
        for(Goods goods : goodsList) {
            series1.getData().add(new XYChart.Data(goods.getName(), goods.getInventory()));
            series2.getData().add(new XYChart.Data(goods.getName(), goods.getMaxnum()));
            series3.getData().add(new XYChart.Data(goods.getName(), goods.getMinnum()));
        }
        barChartData1.addAll(series1, series2, series3);
        bc1.setData(barChartData1);


        bc2.setTitle("类别信息条形图");
        xAxis2.setLabel("类别名称");
        yAxis2.setLabel("产品种数");
        List<Integer[]> typeNum =  goodsDAO.getTypeNum();
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        series.setName("产品种数");
        for (Integer[] objects : typeNum) {
            String typeName = goodsTypeDAO.getTypeName(objects[0]).getTypeName();
            series.getData().add(new XYChart.Data(typeName, objects[1]));
        }
        barChartData2.add(series);
        bc2.setData(barChartData2);
    }

    public void displaySupplierChart() {
        barChartData1.clear();
        bc1.setTitle("供应商信息条形图");
        xAxis1.setLabel("供应商");
        yAxis1.setLabel("货源数量数量");
        List<Map> viewList = supplierDAOimpl.selectSupplierGoodsinfo();
        XYChart.Series<String, Integer> series1 = new XYChart.Series();
        XYChart.Series<String, Integer> series2 = new XYChart.Series();
        series1.setName("进货量");
        series2.setName("货存量");
        for (Map map : viewList) {
            series1.getData().add(new XYChart.Data(map.get("name"), map.get("sumStorageNumber")));
            series2.getData().add(new XYChart.Data(map.get("name"), map.get("sumGoodsInventory")));
        }
        barChartData1.addAll(series1, series2);
        bc1.setData(barChartData1);
    }

    public void displayStorageChart() {
        barChartData1.clear();
        bc1.setTitle("入库信息条形图");
        xAxis1.setLabel("入库员");
        yAxis1.setLabel("入库总数");
        Map<String, Integer> map = new HashMap();
        List<Storage> storageList = storageDAOimpl.selectAll();
        for (Storage storage : storageList) {
            if (!isMapInclude(storage, Storage.class, map))
                map.put(storage.getOperator(), storage.getNumber());
        }
        XYChart.Series<String, Integer> series = new XYChart.Series();
        series.setName("出货员");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            series.getData().add(new XYChart.Data(entry.getKey(), entry.getValue()));
        }
        barChartData1.add(series);
        bc1.setData(barChartData1);

    }

    public void displayRetrievalChart() {
        bc1.setTitle("出库信息条形图");
        xAxis1.setLabel("出库员");
        yAxis1.setLabel("出库总数");
        XYChart.Series<String, Integer> series = new XYChart.Series();
        Map<String, Integer> map = new HashMap();
        List<Retrieval> retrievalList = retrievalDAOimpl.selectAll();
        for (Retrieval retrieval : retrievalList) {
            if (!isMapInclude(retrieval, Retrieval.class, map))
                map.put(retrieval.getOperator(), retrieval.getNumber());
        }

        series.setName("出货员");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            series.getData().add(new XYChart.Data(entry.getKey(), entry.getValue()));
        }
        barChartData1.clear();
        barChartData1.add(series);
        bc1.setData(barChartData1);

    }

    public void displayUserChart() {
        barChartData1.clear();
        bc1.setTitle("用户信息条形图");
        xAxis1.setLabel("角色");
        yAxis1.setLabel("数量");
        List<User> userList =  userDAOimpl.selectAll();
        XYChart.Series<String, Integer> series = new XYChart.Series();
        series.setName("角色类型");
        int manager, salesman, buyer, viewer;
        manager = salesman = buyer = viewer = 0;
        for (User user : userList) {
            switch (user.getRole()) {
                case "管理员":
                    manager++;
                    break;
                case "出货员":
                    salesman++;
                    break;
                case "进货员":
                    buyer++;
                    break;
                
            }
        }
        series.getData().add(new XYChart.Data("管理员", manager));
        series.getData().add(new XYChart.Data("进货员", salesman));
        series.getData().add(new XYChart.Data("出货员", buyer));
        
        barChartData1.add(series);
        bc1.setData(barChartData1);
    }

    private boolean isMapInclude(Object o, Class c, Map<String, Integer> map) {
        boolean isfound = false;
        String operator;
        int number;
        if (c.equals(Retrieval.class)) {
            operator = ((Retrieval) o).getOperator();
            number = ((Retrieval) o).getNumber();
        }
        else {
            operator = ((Storage) o).getOperator();
            number = ((Storage) o).getNumber();
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (operator.equals(entry.getKey())) {
                int value = entry.getValue() + number;
                entry.setValue(value);
                isfound = true;
            }
        }
        return isfound;
    }
}
