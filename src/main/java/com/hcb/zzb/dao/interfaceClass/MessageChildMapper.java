package com.hcb.zzb.dao.interfaceClass;

import java.util.List;
import java.util.Map;

import com.hcb.zzb.dto.MessageChild;

public interface MessageChildMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MessageChild record);

    int insertSelective(MessageChild record);

    MessageChild selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MessageChild record);

    int updateByPrimaryKey(MessageChild record);
    
    List<MessageChild> selectAllMessageByUser(Map<String,Object> map);
    
    int selectAllMessageByUserCount(String userUuid);
    
    MessageChild selectByUserUuidMessageChildUuid(Map<String,Object> map);
    
    List<MessageChild> selectMessageChildByMessageBaseUuid(String messageBaseUUid);
}