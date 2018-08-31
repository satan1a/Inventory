package com.satan1a.inventory.dao.impl;

import com.satan1a.inventory.dao.GoodsTypeDAO;
import com.satan1a.inventory.model.GoodsType;
import com.satan1a.inventory.until.HibernateUtil;
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
