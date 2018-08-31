package cn.db.inventory.dao;

import cn.db.inventory.model.Supplier;

import java.util.List;
import java.util.Map;

public interface SupplierDAO {
    /**
     * Get all supplier
     *
     * @return List<Supplier>
     */
    public List<Supplier> selectAll();

    /**
     * delete supplier from mysql
     *
     * @param supplier
     * @return true if delete successful
     */
    public boolean delete(Supplier supplier);

    /**
     * add supplier to mysql
     *
     * @param supplier
     * @return true if add supplier to mysql successful
     */
    public boolean add(Supplier supplier);

    /**
     * update supplier
     *
     * @param supplier
     * @return true if update supplier successful
     */
    public boolean update(Supplier supplier);

    /**
     * select supplier by id
     *
     * @param id
     * @return List<Supplier> if select successful
     */
    public List<Supplier> selectById(String id);

    /**
     * select supplier by name
     *
     * @param name
     * @return List<Supplier> if select successful
     */
    public List<Supplier> selectByName(String name);

    /**
     * select supplier by contactMan
     *
     * @param contactMan
     * @return List<Supplier>
     */
    public List<Supplier> selectByContactMan(String contactMan);

    /**
     * select all supplier with whose sum of storage number and inventory number
     *
     * @return List<Map> if select successful
     * supplier id mapped key 'id'
     * supplier name mapped key 'name'
     * storage number mapped key 'sumStorageNumber'
     * inventory number mapped key 'sumGoodsInventory'
     */
    public List<Map> selectSupplierGoodsinfo();
}
