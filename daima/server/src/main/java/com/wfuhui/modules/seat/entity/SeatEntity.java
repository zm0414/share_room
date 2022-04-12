package com.wfuhui.modules.seat.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 座位
 * 
 * @author lizhengle
 * @email 1197827306@qq.com
 */
public class SeatEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//店铺id
	private Integer storeId;
	//房间id
	private Integer roomId;
	//座位编号
	private String seatNo;
	//
	private Integer xcoord;
	//
	private Integer ycoord;
	//
	private Integer status;
	//创建时间
	private Date createTime;
	
	private RoomEntity room;
	
	private BigDecimal price;

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
	 * 设置：
	 */
	public void setXcoord(Integer xcoord) {
		this.xcoord = xcoord;
	}
	/**
	 * 获取：
	 */
	public Integer getXcoord() {
		return xcoord;
	}
	/**
	 * 设置：
	 */
	public void setYcoord(Integer ycoord) {
		this.ycoord = ycoord;
	}
	/**
	 * 获取：
	 */
	public Integer getYcoord() {
		return ycoord;
	}
	/**
	 * 设置：
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：
	 */
	public Integer getStatus() {
		return status;
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
	
	public RoomEntity getRoom() {
		return room;
	}
	
	public void setRoom(RoomEntity room) {
		this.room = room;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
}
