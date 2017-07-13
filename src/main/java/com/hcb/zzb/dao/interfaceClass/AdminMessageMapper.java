package com.hcb.zzb.dao.interfaceClass;

import com.hcb.zzb.dto.AdminMessage;

public interface AdminMessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminMessage record);

    int insertSelective(AdminMessage record);

    AdminMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdminMessage record);

    int updateByPrimaryKey(AdminMessage record);
}