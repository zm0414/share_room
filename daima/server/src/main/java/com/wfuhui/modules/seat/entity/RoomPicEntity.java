package com.wfuhui.modules.seat.entity;

import java.io.Serializable;

/**
 * 房间图片
 * 
 * @author evanluns
 * @email 1197827306@qq.com
 */
public class RoomPicEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//
	private Integer id;
	// 房间ID
	private Integer roomId;
	// 图片
	private String picUrl;

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

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	
}
