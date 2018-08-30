package cn.db.inventory.dao;

import cn.db.inventory.model.Retrieval;

import java.util.Date;
import java.util.List;

public interface RetrievalDAO {
    /**
     * Get all retrieval form mysql
     * @return List<Retrieval>
     */
    public List<Retrieval> selectAll();

    /**
     * delete retrieval form mysql
     * @param retrieval
     * @return true if delete successful
     */
    public boolean delete(Retrieval retrieval);

    
  /**
     * add retrieval to mysql
     * @param retrieval
     * @return true if add retrieval to mysql successful
     */
    public boolean add(Retrieval retrieval);

    /**
     * update retrieval
     * @param retrieval
     * @return true if update retrieval successful
     */
    public boolean update(Retrieval retrieval);

    /**
     * select retrieval by id
     * @param id
     * @return Retrieval if select successful
     */
    public List<Retrieval> selectById(String id);

    /**
     * select retrieval by GoodsId
     * @param goodsId
     * @return List<Retrieval> if select successful
     */
    public List<Retrieval> selectByGoodsId(String goodsId);

    /**
     * select retrieval after the date
     * @param date
     * @return List<Retrieval> if select successful
     */
    public List<Retrieval> selectByDateAfter(Date date);
}
