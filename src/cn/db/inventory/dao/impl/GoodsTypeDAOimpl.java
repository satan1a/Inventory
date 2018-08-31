package cn.db.inventory.dao.impl;

import cn.db.inventory.dao.GoodsTypeDAO;
import cn.db.inventory.dao.SupplierDAO;
import cn.db.inventory.model.Goods;
import cn.db.inventory.model.GoodsType;
import cn.db.inventory.model.Supplier;
import cn.db.inventory.until.HibernateUtil;
import cn.db.inventory.until.SessionUtil;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class GoodsTypeDAOimpl implements GoodsTypeDAO {

    @Override
    public GoodsType getTypeName(int id) {
        Session session = HibernateUtil.getSession();
        List<GoodsType> list = session.createCriteria(GoodsType.class)
                .add(Restrictions.eq("id", id)).list();
        return list.get(0);
    }
}
