package com.wfuhui.modules.seat.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 浏览记录
 * 
 * @author lizhengle
 * @email 1197827306@qq.com
 * @date 2021-02-18 20:56:12
 */
public class HistoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//房间id
	private Integer roomId;
	//会员id
	private Long memberId;
	//创建时间
	private Date createTime;
	
	private Integer watch;

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
	 * 设置：会员id
	 */
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	/**
	 * 获取：会员id
	 */
	public Long getMemberId() {
		return memberId;
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
	
	public Integer getWatch() {
		return watch;
	}
	
	public void setWatch(Integer watch) {
		this.watch = watch;
	}
	
}
