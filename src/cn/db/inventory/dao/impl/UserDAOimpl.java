

package cn.db.inventory.dao.impl;

import cn.db.inventory.dao.UserDAO;
import cn.db.inventory.model.User;
import cn.db.inventory.until.HibernateUtil;
import cn.db.inventory.until.SessionUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;


public class UserDAOimpl implements UserDAO{
    SessionUtil sessionUtil = new SessionUtil();

    /**
     * @param userName
     * @param password
     * @return User if the userName and passWord is all right
     */
    @Override
    public User CheckUser(String userName, String password) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria criteria = session.createCriteria(User.class);
            criteria.add(Restrictions.eq("username", userName));
            criteria.add(Restrictions.eq("password", password));
            List<User> userList = criteria.list();
            if (userList.size() > 0) {
                User user = userList.get(0);
                return user;
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    /**
     * Get all users
     * @return List<User>
     */
    @Override
    public List<User> selectAll() {
        return sessionUtil.selectAll(User.class);
    }

    /**
     * delete user from mysql
     * @param user
     * @return true if delete successful
     */
    @Override
    public boolean delete(User user) {
        if (sessionUtil.delete(user)) return true;
        else return false;
    }

    /**
     * add user to mysql
     * @param user
     * @return true if add user to mysql successful
     */
    @Override
    public boolean add(User user) {
        if (sessionUtil.add(user)) return true;
        else return false;
    }

    /**
     * update user
     * @param user
     * @return true if update user successful
     */
    @Override
    public boolean update(User user) {
        if (sessionUtil.update(user)) return true;
        return false;
    }

    /**
     * select user by id
     * @param id
     * @return User
     */
    @Override
    public List<User> selectById(String id) {
        return sessionUtil.selectById(id, User.class);
    }

    /**
     * select supplier by role
     * @param role
     * @return List<User> if select successful
     */
    @Override
    public List<User> selectByRole(String role) {
        Session session = HibernateUtil.getSession();
        List<User> userList = session.createCriteria(User.class)
                .add(Restrictions.eq("role", role)).list();
        return userList;
    }

    /**
     * select user by like name
     * @param username
     * @return List<User>
     */
    @Override
    public List<User> selectByName(String username) {
        Session session = HibernateUtil.getSession();
        List<User> userList = session.createCriteria(User.class)
                .add(Restrictions.like("username", "%" + username + "%")).list();
        return userList;
    }
}
