package com.hcb.zzb.dao.interfaceClass;

import com.hcb.zzb.dto.AttachmentFileInfo;

public interface AttachmentFileInfoMapper {

    int deleteByPrimaryKey(Integer fakeId);

    int insert(AttachmentFileInfo record);

    int insertSelective(AttachmentFileInfo record);

    AttachmentFileInfo selectByPrimaryKey(Integer fakeId);

    int updateByPrimaryKeySelective(AttachmentFileInfo record);

    int updateByPrimaryKey(AttachmentFileInfo record);
    
    AttachmentFileInfo selectByFileUuid(String fileUuid);

}
