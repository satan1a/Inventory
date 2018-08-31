package com.satan1a.inventory.model;

import javax.persistence.*;

@Entity
@Table(name = "goods_type", schema = "inventory", catalog = "")
public class GoodsType {
    private int id;
    private String typeName;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "type_name", nullable = true, length = 255)
    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoodsType goodsType = (GoodsType) o;

        if (id != goodsType.id) return false;
        if (typeName != null ? !typeName.equals(goodsType.typeName) : goodsType.typeName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (typeName != null ? typeName.hashCode() : 0);
        return result;
    }
}
