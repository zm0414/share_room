package com.wfuhui.modules.order.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * 
 * @author evanluns
 * @email 1197827306@qq.com
 */
public class OrderRoomEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//
	private Integer id;
	// 订单ID
	private Integer orderId;
	// 房间ID
	private Integer roomId;

	private Integer num;

	private String picUrl;

	private BigDecimal price;

	private String roomName;
	
	private String seatNo;
	
	private String seatId;
	

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
	 * 设置：订单ID
	 */
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	/**
	 * 获取：订单ID
	 */
	public Integer getOrderId() {
		return orderId;
	}

	/**
	 * 设置：房间ID
	 */
	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	/**
	 * 获取：房间ID
	 */
	public Integer getRoomId() {
		return roomId;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}

	public String getSeatId() {
		return seatId;
	}

	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}

}
