package com.satan1a.inventory.dao;

import com.satan1a.inventory.model.User;

import java.util.List;


public interface UserDAO {
    /**
     * @param userName
     * @param passWord
     * @return User if the userName and passWord is all right
     */
    public User CheckUser(String userName, String passWord);

    /**
     * Get all users
     *
     * @return List<User>
     */
    public List<User> selectAll();

    /**
     * delete user from mysql
     *
     * @param user
     * @return true if delete successful
     */
    public boolean delete(User user);

    /**
     * add user to mysql
     *
     * @param user
     * @return true if add user to mysql successful
     */
    public boolean add(User user);

    /**
     * update user
     *
     * @param user
     * @return true if update user successful
     */
    public boolean update(User user);

    /**
     * select user by id
     *
     * @param id
     * @return List<User>
     */
    public List<User> selectById(String id);

    /**
     * select user by role
     *
     * @param role
     * @return List<User> if select successful
     */
    public List<User> selectByRole(String role);

    /**
     * select user by like name
     *
     * @param username
     * @return List<User>
     */
    public List<User> selectByName(String username);
}
