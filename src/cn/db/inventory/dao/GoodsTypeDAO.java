package cn.db.inventory.dao;

import cn.db.inventory.model.Goods;
import cn.db.inventory.model.GoodsType;

import java.util.List;

public interface GoodsTypeDAO {

    GoodsType getTypeName(int id);
}
