package com.hcb.zzb.dao.interfaceClass;

import com.hcb.zzb.dto.MessageBase;

public interface MessageBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MessageBase record);

    int insertSelective(MessageBase record);

    MessageBase selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MessageBase record);

    int updateByPrimaryKeyWithBLOBs(MessageBase record);

    int updateByPrimaryKey(MessageBase record);
}