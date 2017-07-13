package com.hcb.zzb.dao.interfaceClass;

import java.util.List;
import java.util.Map;

import com.hcb.zzb.dto.AdminMessage;

public interface AdminMessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminMessage record);

    int insertSelective(AdminMessage record);

    AdminMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdminMessage record);

    int updateByPrimaryKey(AdminMessage record);
    
    List<AdminMessage> searchByMap(Map<String,Object> map);
    
    int countByMap(Map<String,Object> map);
}