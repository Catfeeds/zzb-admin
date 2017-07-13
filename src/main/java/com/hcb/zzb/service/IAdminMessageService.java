package com.hcb.zzb.service;

import java.util.List;
import java.util.Map;

import com.hcb.zzb.dto.AdminMessage;

public interface IAdminMessageService {
	int deleteByPrimaryKey(Integer id);

    int insert(AdminMessage record);

    int insertSelective(AdminMessage record);

    AdminMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdminMessage record);

    int updateByPrimaryKey(AdminMessage record);
}
