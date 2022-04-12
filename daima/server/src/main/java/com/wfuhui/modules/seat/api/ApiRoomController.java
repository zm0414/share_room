package com.wfuhui.modules.seat.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wfuhui.common.annotation.AuthIgnore;
import com.wfuhui.common.utils.Query;
import com.wfuhui.common.utils.R;
import com.wfuhui.modules.seat.entity.RoomEntity;
import com.wfuhui.modules.seat.service.RoomService;

/**
 * 房间接口
 * @author evanluns
 *
 */
@RestController
@RequestMapping("/api/room")
public class ApiRoomController {
	
	@Autowired
	private RoomService roomService;
	
	/**
	 * 查询列表
	 * @param map
	 * @return
	 */
	@AuthIgnore
	@GetMapping("list")
    public R list(@RequestParam Map<String, Object> map){
		Query query = new Query(map);
    	List<RoomEntity> roomList = roomService.queryList(query);
    	Integer total = roomService.queryTotal(map);
        return R.ok().put("roomList", roomList).put("total", total);
    }
    
	/**
	 * 详情
	 * @param id
	 * @return
	 */
	@AuthIgnore
    @GetMapping("detail")
    public R detail(int id){
    	RoomEntity room = roomService.queryObject(id);
    	return R.ok().put("room", room);
    }
	
	
	@GetMapping("recommend")
    public R recommend(@RequestAttribute("userId") Long userId){
    	List<RoomEntity> roomList = roomService.getLike(userId);
        return R.ok().put("roomList", roomList);
    }
    
}
