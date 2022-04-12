package com.wfuhui.modules.seat.service;

import com.wfuhui.modules.seat.entity.HistoryEntity;

import java.util.List;
import java.util.Map;

/**
 * 浏览记录
 * 
 * @author lizhengle
 * @email 1197827306@qq.com
 */
public interface HistoryService {
	
	HistoryEntity queryObject(Integer id);
	
	List<HistoryEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(HistoryEntity history);
	
	void update(HistoryEntity history);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
