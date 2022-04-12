package com.wfuhui.modules.seat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wfuhui.modules.seat.entity.RoomEntity;
import com.wfuhui.modules.sys.dao.BaseDao;

/**
 * 房间
 * 
 * @author evanluns
 * @email 1197827306@qq.com
 * @date 2017-07-14 13:43:12
 */
@Mapper
public interface RoomDao extends BaseDao<RoomEntity> {

	void updateStatus(@Param("status")Integer status, @Param("ids")Integer[] ids);

	List<RoomEntity> queryByIds(Integer[] id);
	
	void delStock(@Param("id")Integer id, @Param("stock")int stock);

	void addStock(@Param("id")Integer id, @Param("stock")int stock);
}
