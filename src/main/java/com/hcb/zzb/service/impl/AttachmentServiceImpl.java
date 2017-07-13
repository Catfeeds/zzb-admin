package com.hcb.zzb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcb.zzb.dao.interfaceClass.AttachmentFileInfoMapper;
import com.hcb.zzb.dto.AttachmentFileInfo;
import com.hcb.zzb.service.IAttachmentService;
@Service("AttachmentService")
public class AttachmentServiceImpl implements IAttachmentService{

	@Autowired
	AttachmentFileInfoMapper attachmentFileInfoMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer fakeId) {
		return attachmentFileInfoMapper.deleteByPrimaryKey(fakeId);
	}

	@Override
	public int insert(AttachmentFileInfo record) {
		return attachmentFileInfoMapper.insert(record);
	}

	@Override
	public int insertSelective(AttachmentFileInfo record) {
		return attachmentFileInfoMapper.insertSelective(record);
	}

	@Override
	public AttachmentFileInfo selectByPrimaryKey(Integer fakeId) {
		return attachmentFileInfoMapper.selectByPrimaryKey(fakeId);
	}

	@Override
	public int updateByPrimaryKeySelective(AttachmentFileInfo record) {
		return attachmentFileInfoMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(AttachmentFileInfo record) {
		return attachmentFileInfoMapper.updateByPrimaryKey(record);
	}

	@Override
	public AttachmentFileInfo selectByFileUuid(String fileUuid) {
		return attachmentFileInfoMapper.selectByFileUuid(fileUuid)
				;
	}

}
