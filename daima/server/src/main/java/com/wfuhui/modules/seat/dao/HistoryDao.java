package com.wfuhui.modules.seat.dao;

import com.wfuhui.modules.seat.entity.HistoryEntity;
import com.wfuhui.modules.sys.dao.BaseDao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

/**
 * 浏览记录
 * 
 * @author lizhengle
 * @email 1197827306@qq.com
 */
@Mapper
public interface HistoryDao extends BaseDao<HistoryEntity> {

	List<HistoryEntity> queryByUserId(Long userId);

	List<HistoryEntity> queryByRoomId(Integer roomId);
	
}
