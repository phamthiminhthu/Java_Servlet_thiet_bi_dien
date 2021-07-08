package model;

import java.util.Date;

public class Product {
    private int id;
    private String name;
    private double price;
    private String image;
    private String introduction;
    private String specification;
    private boolean soldOut;
    private int guarantee;
    private int bought;
    private Date createTime;
    private int promotion;
    private boolean deleted;
    private int categoryId;

    public Product() {
    }

    public Product(int id, String name, double price, String image, String introduction, String specification, boolean soldOut, int guarantee, int bought, Date createTime, int promotion, boolean deleted, int categoryId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.introduction = introduction;
        this.specification = specification;
        this.soldOut = soldOut;
        this.guarantee = guarantee;
        this.bought = bought;
        this.createTime = createTime;
        this.promotion = promotion;
        this.deleted = deleted;
        this.categoryId = categoryId;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public boolean isSoldOut() {
        return soldOut;
    }

    public void setSoldOut(boolean soldOut) {
        this.soldOut = soldOut;
    }

    public int getguarantee() {
        return guarantee;
    }

    public void setguarantee(int guarantee) {
        this.guarantee = guarantee;
    }

    public int getBought() {
        return bought;
    }

    public void setBought(int bought) {
        this.bought = bought;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getPromotion() {
        return promotion;
    }

    public void setPromotion(int promotion) {
        this.promotion = promotion;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", introduction='" + introduction + '\'' +
                ", specification='" + specification + '\'' +
                ", sold_out=" + soldOut +
                ", guarantee=" + guarantee +
                ", bought=" + bought +
                ", createTime=" + createTime +
                ", promotion=" + promotion +
                ", deleted=" + deleted +
                ", categoryId=" + categoryId +
                '}';
    }


}
