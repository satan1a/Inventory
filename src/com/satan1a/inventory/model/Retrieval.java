package com.satan1a.inventory.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Retrieval {
	
    private String id;
    private String goodsId;
    private Date date;
    private double outPrice;
    private int number;
    private String consignee;
    private String operator;

    @Id
    @Column(name = "id", nullable = false, length = 11)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
    @Column(name = "date", nullable = true)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "outPrice", nullable = false, precision = 0)
    public double getOutPrice() {
        return outPrice;
    }

    public void setOutPrice(double outPrice) {
        this.outPrice = outPrice;
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
    @Column(name = "consignee", nullable = true, length = 255)
    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
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
        Retrieval retrieval = (Retrieval) o;
        return Double.compare(retrieval.outPrice, outPrice) == 0 &&
                number == retrieval.number &&
                Objects.equals(id, retrieval.id) &&
                Objects.equals(goodsId, retrieval.goodsId) &&
                Objects.equals(date, retrieval.date) &&
                Objects.equals(consignee, retrieval.consignee) &&
                Objects.equals(operator, retrieval.operator);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, goodsId, date, outPrice, number, consignee, operator);
    }
}
