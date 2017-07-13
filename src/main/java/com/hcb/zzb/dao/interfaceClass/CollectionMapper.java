package com.hcb.zzb.dao.interfaceClass;

import java.util.List;
import java.util.Map;

import com.hcb.zzb.dto.Collection;

public interface CollectionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Collection record);

    int insertSelective(Collection record);

    Collection selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Collection record);

    int updateByPrimaryKey(Collection record);
    
    List<Collection> selectByUserUuid(Map<String, Object> map);
    
    int selectByUserUuidCount(String user_uuid);
    
    int deleteByCarUuid(String carUuid);
}