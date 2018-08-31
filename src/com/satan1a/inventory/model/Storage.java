package com.satan1a.inventory.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Storage {
    private String id;
    private String goodsId;
    private String supplierId;
    private int number;
    private int type;
    private Date data;
    private String operator;
    private String name;

   
    @Id
    @Column(name = "id", nullable = false, length = 255)
    public String getId() {
        return id;
    }
    

    public void setId(String id) {
        this.id = id;
    }
    
    
    public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	@Basic
    @Column(name = "goodsId", nullable = true, length = 255)
    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    @Basic
    @Column(name = "supplierId", nullable = true, length = 255)
    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    @Basic
    @Column(name = "number", nullable = false)
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


    @Basic
    @Column(name = "data", nullable = true)
    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Basic
    @Column(name = "operator", nullable = true, length = 255)
    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Storage storage = (Storage) o;
        return number == storage.number &&
                Double.compare(storage.type, type) == 0 &&
                Objects.equals(id, storage.id) &&
                Objects.equals(goodsId, storage.goodsId) &&
                Objects.equals(supplierId, storage.supplierId) &&
                Objects.equals(data, storage.data) &&
                Objects.equals(operator, storage.operator)&&
                Objects.equals(type, storage.type);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, goodsId, supplierId, number,type, data, operator,name);
    }
}
