package com.hcb.zzb.service;

import java.util.List;
import java.util.Map;

import com.hcb.zzb.dto.Manager;
/**
 * 后台系统账号
 * @author kk
 *
 */
public interface IManagerService {

	int deleteByPrimaryKey(Integer id);

    int insert(Manager record);

    int insertSelective(Manager record);

    Manager selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Manager record);

    int updateByPrimaryKey(Manager record);
    /**
     * 根据账号名字查询账号
     * @param account
     * @return
     */
    Manager selectByAccount(String account);
    /**
     * 根据筛选条件查询
     * @param map
     * @return
     */
    List<Manager> searchByMap(Map<String,Object> map);
    /**
     * 根据筛选条件查询数目
     * @param map
     * @return
     */
    int countByMap(Map<String,Object> map);

	Manager selectByAccountUuid(String string);
}
