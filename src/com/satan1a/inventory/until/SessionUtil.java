package com.satan1a.inventory.until;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class SessionUtil {

    public List selectAll(Class c) {
        Session session = HibernateUtil.getSession();
        Criteria cr = session.createCriteria(c);
        List objectList = cr.list();
        session.close();
        return objectList;
    }

    public List selectById(String id, Class c) {
        Session session = HibernateUtil.getSession();
        List list = session.createCriteria(c)
                .add(Restrictions.eq("id", id)).list();
        return list;
    }

    public boolean add(Object o) {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        try {
            session.saveOrUpdate(o);
        } catch (HibernateException e) {
            DebugLog.Log("SQL:create" + o.getClass().getSimpleName() + " fail!");
            return false;
        }
        tx.commit();
        session.close();
        return true;
    }

    public boolean update(Object o) {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        try {
            session.merge(o);
        } catch (HibernateException e) {
            DebugLog.Log("SQL:update" + o.getClass().getSimpleName() + " fail!");
            return false;
        }
        tx.commit();
        session.close();
        return true;
    }

    public boolean delete(Object o) {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        try {
            session.delete(o);
        } catch (HibernateException e) {
            DebugLog.Log("SQL:delete" + o.getClass().getSimpleName() + " fail!");
            return false;
        }
        tx.commit();
        session.close();
        return true;
    }

}
