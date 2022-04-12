package com.wfuhui.modules.seat.service;

import com.wfuhui.modules.seat.entity.SessionsEntity;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 场次
 * 
 * @author lizhengle
 * @email 1197827306@qq.com
 */
public interface SessionsService {
	
	SessionsEntity queryObject(Integer id);
	
	List<SessionsEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SessionsEntity sessions);
	
	void update(SessionsEntity sessions);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

	void init(Date date, Integer storeId, Integer roomId);
}
