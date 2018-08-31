package cn.db.inventory.dao.impl;

import cn.db.inventory.dao.GoodsDAO;
import cn.db.inventory.model.Goods;
import cn.db.inventory.model.TypeCount;
import cn.db.inventory.until.HibernateUtil;
import cn.db.inventory.until.SessionUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("unused")
public class GoodsDAOimpl implements GoodsDAO {

    SessionUtil sessionUtil = new SessionUtil();

    /**
     * Get all goods
     *
     * @return List<Goods>
     */
    @Override
    public List<Goods> selectAll() {
        return sessionUtil.selectAll(Goods.class);
    }

    /**
     * delete goods
     *
     * @param goods
     */
    @Override
    public boolean delete(Goods goods) {
        if (sessionUtil.delete(goods)) return true;
        else return false;
    }

    /**
     * add goods to mysql
     *
     * @param goods
     * @return true if add goods successful
     */
    @Override
    public boolean add(Goods goods) {
        if (sessionUtil.add(goods)) return true;
        else return false;
    }

    /**
     * update goods
     *
     * @param goods
     * @return true if update goods successful
     */
    @Override
    public boolean update(Goods goods) {
        if (sessionUtil.update(goods)) return true;
        return false;
    }

    /**
     * select goods by id
     *
     * @param id
     * @return Goods if select successful
     */
    @Override
    public List<Goods> selectById(String id) {
        return sessionUtil.selectById(id, Goods.class);
    }

    /**
     * select goods by name
     *
     * @param name
     * @return List<Goods> if select successful
     */
    @Override
    public List<Goods> selectByName(String name) {
        Session session = HibernateUtil.getSession();
        List goodsList = session.createCriteria(Goods.class)
                .add(Restrictions.like("name", "%" + name + "%")).list();
        return goodsList;
    }

    /**
     * select goods by type
     *
     * @param type
     * @return List<Goods> if select successful
     */
    @Override
    public List<Goods> selectByType(int type) {
        Session session = HibernateUtil.getSession();
        List<Goods> goodsList = session.createCriteria(Goods.class)
                .add(Restrictions.eq("type", type)).list();
        return goodsList;
    }

    /**
     * get the count group by goods type.
     *
     * @return the list of type.
     */
    @Override
    public List<Integer[]> getTypeNum() {
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery("select type, count(*) from Goods group by type");
        List<Object[]> list = query.list();
        List<Integer[]> data = new ArrayList<>();
        for (Object[] objects : list) {
            Integer[] integers = new Integer[objects.length];
            for (int i = 0; i < objects.length; i++) {
                integers[i] = Integer.valueOf(objects[i].toString());
            }
            data.add(integers);
        }
        return data;
    }

}
