package com.hcb.zzb.service;

import com.hcb.zzb.dto.AttachmentFileInfo;

public interface IAttachmentService {

   public int deleteByPrimaryKey(Integer fakeId);

   public int insert(AttachmentFileInfo record);

   public int insertSelective(AttachmentFileInfo record);

   public AttachmentFileInfo selectByPrimaryKey(Integer fakeId);

   public int updateByPrimaryKeySelective(AttachmentFileInfo record);

   public int updateByPrimaryKey(AttachmentFileInfo record);
    
   public AttachmentFileInfo selectByFileUuid(String fileUuid);

}
