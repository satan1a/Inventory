package com.satan1a.inventory.dao.impl;

import com.satan1a.inventory.dao.RetrievalDAO;
import com.satan1a.inventory.model.Goods;
import com.satan1a.inventory.model.Retrieval;
import com.satan1a.inventory.until.HibernateUtil;
import com.satan1a.inventory.until.SessionUtil;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.Date;
import java.util.List;

public class RetrievalDAOimpl implements RetrievalDAO {
    SessionUtil sessionUtil = new SessionUtil();

    /**
     * Get all retrieval form mysql
     *
     * @return List<Retrieval>
     */
    @Override
    public List<Retrieval> selectAll() {
        return sessionUtil.selectAll(Retrieval.class);
    }

    /**
     * delete retrieval form mysql
     *
     * @param retrieval
     * @return true if delete successful
     */
    @Override
    public boolean delete(Retrieval retrieval) {
        if (sessionUtil.delete(retrieval)) return true;
        else return false;
    }

    /**
     * add retrieval to mysql
     *
     * @param retrieval
     * @return true if add retrieval to mysql successful
     */
    @Override
    public boolean add(Retrieval retrieval) {
        if (sessionUtil.add(retrieval)) {
            GoodsDAOimpl goodsDAOimpl = new GoodsDAOimpl();
            List<Goods> goodsList = goodsDAOimpl.selectById(retrieval.getGoodsId());
            if (goodsList.size() > 0) {
                Goods goods = goodsList.get(0);
                goods.setInventory(goods.getInventory() - retrieval.getNumber());
                goodsDAOimpl.update(goods);
            }
            return true;
        } else return false;
    }

    /**
     * update retrieval
     *
     * @param retrieval
     * @return true if update retrieval successful
     */
    @Override
    public boolean update(Retrieval retrieval) {
        if (sessionUtil.update(retrieval)) return true;
        return false;
    }

    /**
     * select retrieval by id
     *
     * @param id
     * @return Retrieval if select successful
     */
    @Override
    public List<Retrieval> selectById(String id) {
        return sessionUtil.selectById(id, Retrieval.class);
    }

    /**
     * select retrieval by GoodsId
     *
     * @param goodsId
     * @return List<Retrieval> if select successful
     */
    @Override
    public List<Retrieval> selectByGoodsId(String goodsId) {
        Session session = HibernateUtil.getSession();
        List<Retrieval> retrievalList = session.createCriteria(Retrieval.class)
                .add(Restrictions.like("id", "%" + goodsId + "%")).list();
        return retrievalList;
    }

    /**
     * select retrieval after the date
     *
     * @param date
     * @return List<Retrieval> if select successful
     */
    @Override
    public List<Retrieval> selectByDateAfter(Date date) {
        Session session = HibernateUtil.getSession();
        List<Retrieval> retrievalList = session.createCriteria(Retrieval.class)
                .add(Restrictions.ge("date", date)).list();
        return retrievalList;
    }
}
