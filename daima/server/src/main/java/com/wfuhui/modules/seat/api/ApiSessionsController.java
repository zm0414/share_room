package com.wfuhui.modules.seat.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wfuhui.common.annotation.AuthIgnore;
import com.wfuhui.common.utils.DateUtils;
import com.wfuhui.common.utils.R;
import com.wfuhui.modules.seat.entity.SessionsEntity;
import com.wfuhui.modules.seat.service.SessionsService;

/**
 * 座位接口
 * @author evanluns
 *
 */
@RestController
@RequestMapping("/api/sessions")
public class ApiSessionsController {
	
	@Autowired
	private SessionsService sessionsService;
	
	/**
	 * 查询列表
	 * @param map
	 * @return
	 */
	@AuthIgnore
	@GetMapping("list")
    public R list(@RequestParam Map<String, Object> map){
		map.put("sidx", "xcoord, ycoord");
		map.put("order", "asc");
    	List<SessionsEntity> sessionsList = sessionsService.queryList(map);
    	if(sessionsList == null || sessionsList.size() == 0) {
    		sessionsService.init(DateUtils.parseDate(map.get("date").toString()), Integer.parseInt(map.get("storeId").toString()), Integer.parseInt(map.get("roomId").toString()));
    		sessionsList = sessionsService.queryList(map);
    	}
        return R.ok().put("sessionsList", sessionsList);
    }
    
}
