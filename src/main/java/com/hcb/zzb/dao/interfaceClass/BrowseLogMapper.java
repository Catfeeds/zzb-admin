package com.hcb.zzb.dao.interfaceClass;

import java.util.List;
import java.util.Map;

import com.hcb.zzb.dto.BrowseLog;

public interface BrowseLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BrowseLog record);

    int insertSelective(BrowseLog record);

    BrowseLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BrowseLog record);

    int updateByPrimaryKey(BrowseLog record);
    
    List<BrowseLog> selectByUserUuid(Map<String, Object> map);
    
    int selectByUserUuidCount(String user_uuid);
    
    List<BrowseLog> selectByGuessYouLike(Map<String, Object> map);
    
    int selectGuessYouLikeCount(String userUuid);

	List<BrowseLog> selectByGuessYouLike1(String userUuid);

	List<BrowseLog> selectByrecentBrowse(String userUuid);
}