package com.wfuhui.modules.seat.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 场次
 * 
 * @author lizhengle
 * @email 1197827306@qq.com
 */
public class SessionsEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//店铺id
	private Integer storeId;
	//房间id
	private Integer roomId;
	//座位id
	private Integer seatId;
	//座位编号
	private String seatNo;
	//价格
	private BigDecimal price;
	//发布日期
	private Date releaseDate;
	//创建时间
	private Date createTime;
	
	//
	private Integer xcoord;
	//
	private Integer ycoord;
	
	private String type = "danren";
	
	private Integer status;

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：店铺id
	 */
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	/**
	 * 获取：店铺id
	 */
	public Integer getStoreId() {
		return storeId;
	}
	/**
	 * 设置：房间id
	 */
	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}
	/**
	 * 获取：房间id
	 */
	public Integer getRoomId() {
		return roomId;
	}
	/**
	 * 设置：座位id
	 */
	public void setSeatId(Integer seatId) {
		this.seatId = seatId;
	}
	/**
	 * 获取：座位id
	 */
	public Integer getSeatId() {
		return seatId;
	}
	/**
	 * 设置：座位编号
	 */
	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}
	/**
	 * 获取：座位编号
	 */
	public String getSeatNo() {
		return seatNo;
	}
	/**
	 * 设置：价格
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	/**
	 * 获取：价格
	 */
	public BigDecimal getPrice() {
		return price;
	}
	/**
	 * 设置：发布日期
	 */
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	/**
	 * 获取：发布日期
	 */
	public Date getReleaseDate() {
		return releaseDate;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	
	public Integer getXcoord() {
		return xcoord;
	}
	
	public void setXcoord(Integer xcoord) {
		this.xcoord = xcoord;
	}
	
	public Integer getYcoord() {
		return ycoord;
	}
	
	public void setYcoord(Integer ycoord) {
		this.ycoord = ycoord;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
