package com.wfuhui.modules.seat.service;

import com.wfuhui.modules.seat.entity.SeatEntity;

import java.util.List;
import java.util.Map;

/**
 * 座位
 * 
 * @author lizhengle
 * @email 1197827306@qq.com
 */
public interface SeatService {
	
	SeatEntity queryObject(Integer id);
	
	List<SeatEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SeatEntity seat);
	
	void update(SeatEntity seat);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
