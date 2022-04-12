package com.wfuhui.modules.seat.dao;

import org.apache.ibatis.annotations.Mapper;

import com.wfuhui.modules.seat.entity.RoomPicEntity;
import com.wfuhui.modules.sys.dao.BaseDao;

/**
 * 房间图片
 * 
 * @author evanluns
 * @email 1197827306@qq.com
 * @date 2017-07-14 13:43:12
 */
@Mapper
public interface RoomPicDao extends BaseDao<RoomPicEntity> {

	String[] queryByRoomId(Integer roomId);

	void deleteByRoomId(Integer roomId);
	
}
