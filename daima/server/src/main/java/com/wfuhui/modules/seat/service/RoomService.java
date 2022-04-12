package com.wfuhui.modules.seat.service;

import java.util.List;
import java.util.Map;

import com.wfuhui.modules.seat.entity.RoomEntity;

/**
 * 房间
 * 
 * @author evanluns
 * @email 1197827306@qq.com
 */
public interface RoomService {
	
	RoomEntity queryObject(Integer id);
	
	List<RoomEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(RoomEntity room);
	
	void update(RoomEntity room);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
	
	void updateStatus(Integer status, Integer[] ids);

	List<RoomEntity> getLike(Long userId);
}
