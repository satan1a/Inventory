package com.satan1a.inventory.dao;

import com.satan1a.inventory.model.Storage;

import java.util.List;


public interface StorageDAO {
    /**
     * Get all storage form mysql
     *
     * @return List<Storage>
     */
    public List<Storage> selectAll();

    /**
     * delete storage form mysql
     *
     * @param storage
     * @return true if delete storage successful
     */
    public boolean delete(Storage storage);

    /**
     * add storage to mysql
     *
     * @param storage
     * @return true if add storage to mysql successful
     */
    public boolean add(Storage storage);

    /**
     * update storage
     *
     * @param storage
     * @return true if update storage successful
     */
    public boolean update(Storage storage);

    /**
     * select storage by id
     *
     * @param id
     * @return List<Storage> if select successful
     */
    public List<Storage> selectById(String id);

    /**
     * select storage by GoodsId
     *
     * @param goodsId
     * @return List<Storage> if select successful
     */
    public List<Storage> selectByGoodsId(String goodsId);

    /**
     * select storage by supplierId
     *
     * @param supplierId
     * @return List<Storage> if select successful
     */
    public List<Storage> selectBySupplierId(String supplierId);

    /**
     * select storage by operator
     *
     * @param operator
     * @return List<Storage> if select successful
     */
    public List<Storage> selectByOperator(String operator);
}
