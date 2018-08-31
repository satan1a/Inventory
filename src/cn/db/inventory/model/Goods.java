package cn.db.inventory.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Goods {
    private String id;
    private String name;
    private Integer type;
    private Integer inventory;
    private Integer maxnum;
    private Integer minnum;
    private String supplierId;

    public void get1(String id, String name, Integer type, Integer inventory) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.inventory = inventory;
    }

    @Id
    @Column(name = "id", nullable = false, length = 255)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "type", nullable = true)
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Basic
    @Column(name = "inventory", nullable = true)
    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    @Basic
    @Column(name = "maxnum", nullable = true)
    public Integer getMaxnum() {
        return maxnum;
    }

    public void setMaxnum(Integer maxnum) {
        this.maxnum = maxnum;
    }

    @Basic
    @Column(name = "minnum", nullable = true)
    public Integer getMinnum() {
        return minnum;
    }

    public void setMinnum(Integer minnum) {
        this.minnum = minnum;
    }

    @Basic
    @Column(name = "supplierId", nullable = true, length = 255)
    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Goods goods = (Goods) o;
        return Objects.equals(id, goods.id) &&
                Objects.equals(name, goods.name) &&
                Objects.equals(type, goods.type) &&
                Objects.equals(inventory, goods.inventory) &&
                Objects.equals(maxnum, goods.maxnum) &&
                Objects.equals(minnum, goods.minnum) &&
                Objects.equals(supplierId, goods.supplierId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, type, inventory, maxnum, minnum, supplierId);
    }

}
