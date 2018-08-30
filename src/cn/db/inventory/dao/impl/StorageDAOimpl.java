package cn.db.inventory.dao.impl;

import cn.db.inventory.dao.StorageDAO;
import cn.db.inventory.model.Goods;
import cn.db.inventory.model.Storage;
import cn.db.inventory.until.HibernateUtil;
import cn.db.inventory.until.SessionUtil;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class StorageDAOimpl implements StorageDAO {
    SessionUtil sessionUtil = new SessionUtil();
    /**
     * Get all storage form mysql
     *
     * @return List<Storage>
     */
    @Override
    public List<Storage> selectAll() {
        return sessionUtil.selectAll(Storage.class);
    }

    /**
     * delete storage form mysql
     *
     * @param storage
     * @return true if delete storage successful
     */
    @Override
    public boolean delete(Storage storage) {
        if (sessionUtil.delete(storage)) return true;
        else return false;
    }

    /**
     * add storage to mysql
     *
     * @param storage
     * @return true if add storage to mysql successful
     */
    @Override
    public boolean add(Storage storage) {
        if (sessionUtil.add(storage)) {
            GoodsDAOimpl goodsDAOimpl = new GoodsDAOimpl();
            List<Goods> goodsList = goodsDAOimpl.selectById(storage.getGoodsId());
            if (goodsList.size() > 0) {
                Goods goods = goodsList.get(0);
                goods.setInventory(goods.getInventory() + storage.getNumber());
                goodsDAOimpl.update(goods);
            
            }
            else {
            	Goods good = new Goods();
            	good.get1(storage.getGoodsId(), storage.getName(),storage.getType() , storage.getNumber());
            	good.setMaxnum(0);
            	good.setMinnum(0);
                GoodsDAOimpl goodsDAOimpl2 = new GoodsDAOimpl();
                goodsDAOimpl2.add(good);
            	

            	 
			}
            return true;
        }
        else return false;
    }

    /**
     * update storage
     *
     * @param storage
     * @return true if update storage successful
     */
    @Override
    public boolean update(Storage storage) {
        if (sessionUtil.update(storage)) return true;
        return false;
    }

    /**
     * select storage by id
     *
     * @param id
     * @return Storage if select successful
     */
    @Override
    public List<Storage> selectById(String id) {
        return sessionUtil.selectById(id, Storage.class);
    }

    /**
     * select storage by GoodsId
     *
     * @param goodsId
     * @return List<Storage> if select successful
     */
    @Override
    public List<Storage> selectByGoodsId(String goodsId) {
        Session session = HibernateUtil.getSession();
        List<Storage> storageList = session.createCriteria(Storage.class)
                .add(Restrictions.like("goodsId", "%" + goodsId + "%")).list();
        return storageList;
    }

    /**
     * select storage by supplierId
     *
     * @param supplierId
     * @return List<Storage> if select successful
     */
    @Override
    public List<Storage> selectBySupplierId(String supplierId) {
        Session session = HibernateUtil.getSession();
        List<Storage> storageList = session.createCriteria(Storage.class)
                .add(Restrictions.like("supplierId", "%" + supplierId + "%")).list();
        return storageList;
    }

    /**
     * select storage by operator
     * @param operator
     * @return List<Storage> if select successful
     */
    @Override
    public List<Storage> selectByOperator(String operator) {
        Session session = HibernateUtil.getSession();
        List<Storage> storageList = session.createCriteria(Storage.class)
                .add(Restrictions.like("operator", "%" + operator + "%")).list();
        return storageList;
    }
}
