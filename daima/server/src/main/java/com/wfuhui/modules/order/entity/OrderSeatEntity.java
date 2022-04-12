package com.wfuhui.modules.order.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 订单座位
 * 
 * @author lizhengle
 * @email 1197827306@qq.com
 */
public class OrderSeatEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//订单id
	private Integer orderId;
	//房间id
	private Integer roomId;
	//座位id
	private Integer seatId;
	//座位名称
	private String seatName;
	
	private Integer sessionsId;

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
	 * 设置：订单id
	 */
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	/**
	 * 获取：订单id
	 */
	public Integer getOrderId() {
		return orderId;
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
	 * 设置：座位名称
	 */
	public void setSeatName(String seatName) {
		this.seatName = seatName;
	}
	/**
	 * 获取：座位名称
	 */
	public String getSeatName() {
		return seatName;
	}
	
	public Integer getSessionsId() {
		return sessionsId;
	}
	
	public void setSessionsId(Integer sessionsId) {
		this.sessionsId = sessionsId;
	}
	
}
