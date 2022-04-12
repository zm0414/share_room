package com.wfuhui.modules.seat.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wfuhui.common.annotation.AuthIgnore;
import com.wfuhui.common.utils.Query;
import com.wfuhui.common.utils.R;
import com.wfuhui.modules.seat.entity.SeatEntity;
import com.wfuhui.modules.seat.service.SeatService;

/**
 * 座位接口
 * @author evanluns
 *
 */
@RestController
@RequestMapping("/api/seat")
public class ApiSeatController {
	
	@Autowired
	private SeatService seatService;
	
	/**
	 * 查询列表
	 * @param map
	 * @return
	 */
	@AuthIgnore
	@GetMapping("list")
    public R list(@RequestParam Map<String, Object> map){
		Query query = new Query(map);
    	List<SeatEntity> seatList = seatService.queryList(query);
        return R.ok().put("seatList", seatList);
    }
    
}
