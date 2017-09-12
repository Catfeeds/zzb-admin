package com.hcb.zzb.service;

import java.util.List;
import java.util.Map;

import com.hcb.zzb.dto.AdminMessage;
/**
 * 系统消息
 * @author kk
 *
 */
public interface IAdminMessageService {
	int deleteByPrimaryKey(Integer id);

    int insert(AdminMessage record);

    int insertSelective(AdminMessage record);

    AdminMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdminMessage record);

    int updateByPrimaryKey(AdminMessage record);
    /**
     * 根据输入内容查询列表
     * @param map
     * @return
     */
    List<AdminMessage> searchByMap(Map<String,Object> map);
    /**
     * 根据输入内容查询条数
     * @param map
     * @return
     */
    int countByMap(Map<String,Object> map);
}
