package com.satan1a.inventory.dao.impl;

import com.satan1a.inventory.dao.SupplierDAO;
import com.satan1a.inventory.model.Supplier;
import com.satan1a.inventory.until.HibernateUtil;
import com.satan1a.inventory.until.SessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Map;


public class SupplierDAOimpl implements SupplierDAO {
    SessionUtil sessionUtil = new SessionUtil();
    /**
     * Get all supplier
     * @return List<Supplier>
     */
    @Override
    public List<Supplier> selectAll() {
        return sessionUtil.selectAll(Supplier.class);
    }

    /**
     * delete supplier from mysql
     * @param supplier
     * @return true if delete successful
     */
    @Override
    public boolean delete(Supplier supplier) {
        if (sessionUtil.delete(supplier)) return true;
        else return false;
    }

    /**
     * add supplier to mysql
     * @param supplier
     * @return true if add supplier to mysql successful
     */
    @Override
    public boolean add(Supplier supplier) {
        if (sessionUtil.add(supplier)) return true;
        else return false;
    }

    /**
     * update supplier
     * @param supplier
     * @return true if update supplier successful
     */
    @Override
    public boolean update(Supplier supplier) {
        if (sessionUtil.update(supplier)) return true;
        return false;
    }

    /**
     * select supplier by id
     * @param id
     * @return Supplier if select successful
     */
    @Override
    public List<Supplier> selectById(String id) {
        return sessionUtil.selectById(id, Supplier.class);
    }

    /**
     * select supplier by name
     * @param name
     * @return List<Supplier> if select successful
     */
    @Override
    public List<Supplier> selectByName(String name) {
        Session session = HibernateUtil.getSession();
        List<Supplier> supplierList = session.createCriteria(Supplier.class)
                .add(Restrictions.like("name", "%" + name + "%")).list();
        return supplierList;
    }

    /**
     * select supplier by contactMan
     * @param contactMan
     * @return List<Supplier>
     */
    @Override
    public List<Supplier> selectByContactMan(String contactMan) {
        Session session = HibernateUtil.getSession();
        List<Supplier> supplierList = session.createCriteria(Supplier.class)
                .add(Restrictions.like("contactMan", "%" + contactMan + "%")).list();
        return supplierList;
    }

    /**
     * select all supplier with whose sum of storage number and inventory number
     *
     * @return List<Map> if select successful
     * supplier id mapped key 'id'
     * supplier name mapped key 'name'
     * storage number mapped key 'sumStorageNumber'
     * inventory number mapped key 'sumGoodsInventory'
     */
    @Override
    public List<Map> selectSupplierGoodsinfo() {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        List<Map> maps = session.createQuery(
                "select new map(supplier.id as id, supplier.name as name, " +
                        "sum(storage.number) as sumStorageNumber, sum(goods.inventory) as sumGoodsInventory) " +
                        "from Supplier as supplier, Goods as goods, " +
                        "Storage as storage " +
                        "where supplier.id = goods.supplierId and supplier.id = storage.supplierId " +
                        "group by supplier.id").list();
        tx.commit();
        session.close();
        return maps;
    }

}
