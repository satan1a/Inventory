package cn.db.inventory.dao;

import cn.db.inventory.model.Goods;

import java.util.List;

public interface GoodsDAO {

    /**
     * Get all goods
     *
     * @return List<Goods>
     */
    public List<Goods> selectAll();

    /**
     * delete goods
     *
     * @param goods
     */
    public boolean delete(Goods goods);

    /**
     * add goods to mysql
     *
     * @param goods
     * @return true if add goods successful
     */
    public boolean add(Goods goods);

    /**
     * update goods
     *
     * @param goods
     * @return true if update goods successful
     */
    public boolean update(Goods goods);

    /**
     * select goods by id
     *
     * @param id
     * @return List<Goods> if select successful
     */
    public List<Goods> selectById(String id);

    /**
     * select goods by name
     *
     * @param name
     * @return List<Goods> if select successful
     */
    public List<Goods> selectByName(String name);

    /**
     * select goods by type
     *
     * @param type
     * @return List<Goods> if select successful
     */
    public List<Goods> selectByType(int type);

    /**
     * get the count group by goods type.
     *
     * @return the list of type.
     */
    public List<Integer[]> getTypeNum();
}
