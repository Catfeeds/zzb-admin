package com.hcb.zzb.service;

import java.util.List;
import java.util.Map;

import com.hcb.zzb.dto.Manager;

public interface IManagerService {

	int deleteByPrimaryKey(Integer id);

    int insert(Manager record);

    int insertSelective(Manager record);

    Manager selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Manager record);

    int updateByPrimaryKey(Manager record);
    
    Manager selectByAccount(String account);
    
    List<Manager> searchByMap(Map<String,Object> map);
    
    int countByMap(Map<String,Object> map);
}
