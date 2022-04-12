package com.wfuhui.modules.member.dao;

import java.math.BigDecimal;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wfuhui.modules.member.entity.MemberEntity;
import com.wfuhui.modules.sys.dao.BaseDao;


/**
 * 会员
 * 
 * @author evanluns
 * @email 1197827306@qq.com
 * @date 2017-09-17 22:24:01
 */
@Mapper
public interface MemberDao extends BaseDao<MemberEntity> {

	MemberEntity queryByOpenid(String openid);

	MemberEntity queryByLoginName(String loginName);
	
	void delBanlance(@Param("userId")Long userId, @Param("balance")BigDecimal balance);

	void addBanlance(@Param("userId")Long userId, @Param("balance")BigDecimal balance);
}
