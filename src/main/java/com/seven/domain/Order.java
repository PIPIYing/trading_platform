package com.seven.domain;

import java.sql.Timestamp;

public class Order {
  private int id;
  private int uesr_id;
  private int good_id;
  private String userName;
  private String goodName;
  private int amount;
  private int totalPrice;
  private String phone;
  private String address;
  private String remark;
  private Timestamp createTime;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getUesr_id() {
    return uesr_id;
  }

  public void setUesr_id(int uesr_id) {
    this.uesr_id = uesr_id;
  }

  public int getGood_id() {
    return good_id;
  }

  public void setGood_id(int good_id) {
    this.good_id = good_id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getGoodName() {
    return goodName;
  }

  public void setGoodName(String goodName) {
    this.goodName = goodName;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public int getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(int totalPrice) {
    this.totalPrice = totalPrice;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Timestamp createTime) {
    this.createTime = createTime;
  }
}
