package com.wfuhui.modules.seat.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wfuhui.common.utils.Query;
import com.wfuhui.common.utils.R;
import com.wfuhui.modules.seat.entity.RoomEntity;
import com.wfuhui.modules.seat.service.RoomService;


/**
 * 房间
 * 
 * @author evanluns
 * @email 1197827306@qq.com
 * @date 2017-07-14 13:43:12
 */
@RestController
@RequestMapping("/room")
public class RoomController {
	@Autowired
	private RoomService roomService;
	
	@RequestMapping("/listAll")
	public R listAll(@RequestParam Map<String, Object> params){
		List<RoomEntity> roomList = roomService.queryList(params);
		
		return R.ok().put("roomList", roomList);
	}
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<RoomEntity> roomList = roomService.queryList(query);
		int total = roomService.queryTotal(query);
		
		return R.ok().put("rows", roomList).put("total", total);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id){
		RoomEntity room = roomService.queryObject(id);
		
		return R.ok().put("room", room);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody RoomEntity room){
		room.setCreateTime(new Date());
		roomService.save(room);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody RoomEntity room){
		roomService.update(room);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids){
		roomService.deleteBatch(ids);
		
		return R.ok();
	}
	
    @RequestMapping("/lower")
    public R lower(@RequestBody Integer[] ids) {
    	roomService.updateStatus(0, ids);
    	return R.ok();
    }
    
    @RequestMapping("/upper")
    public R upper(@RequestBody Integer[] ids) {
    	roomService.updateStatus(1, ids);
    	return R.ok();
    }
	
	@RequestMapping("/getAll")
	public R getAll() {
		List<RoomEntity> roomList = roomService.queryList(null);
		return R.ok().put("roomList", roomList);
	}
	
}
