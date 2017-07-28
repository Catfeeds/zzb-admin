package com.hcb.zzb.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.hcb.zzb.dto.Users;
import com.hcb.zzb.bean.base.OutHead;
import com.hcb.zzb.dto.AttachmentFileInfo;
import com.hcb.zzb.service.IAttachmentService;
import com.hcb.zzb.service.IUsersService;
import com.hcb.zzb.util.Config;
import com.hcb.zzb.util.MD5Util;
import com.hcb.zzb.util.MtsUtil;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Image;

import net.sf.json.JSONObject;

@RestController
public class AliyunOss {

	
	@Autowired
	IAttachmentService attachments;
	@Autowired
	IUsersService UsersService;
	private String getEndPoint() {
		return Config.getString("endPoint");
	}

	private String getAccessKeyId() {
		return Config.getString("accessKeyId");
	}

	private String getAccessKeySecret() {
		return Config.getString("accessKeySecret");
	}

	private String getBucketName() {
		return Config.getString("bucketName");
	}
	
	public String getBucketName1() {
		return Config.getString("bucketName1");
	}
	
	public String getBucketName2() {
		return Config.getString("bucketName2");
	}
	
	//上传文件
	@RequestMapping(value ="media/file_upload", method = RequestMethod.POST)
	public String fileUpload(HttpServletRequest request,@RequestParam("file") MultipartFile file) {
		
		JSONObject json = new JSONObject();
		// 判断文件是否为空
		if (!file.isEmpty()) {
			try {
				//校验文件类型  
				String contentType = file.getContentType();
				/*if(!"application/pdf".equals(contentType)&&!"application/msword".equals(contentType)
						&&!"text/plain".equals(contentType)&&!"application/vnd.ms-excel".equals(contentType)
						&&!"application/vnd.ms-powerpoint".equals(contentType)&&!"application/excel".equals(contentType)){
					json.put("result", "1");
					json.put("description", "请检查数据类型是否正确");
					return buildReqJsonObject(json);
				}*/
				String fileName = file.getOriginalFilename();
				/*if(!fileName.toUpperCase().endsWith("PDF")&&!fileName.toUpperCase().endsWith("DOC")&&!fileName.toUpperCase().endsWith("DOCX")&&!fileName.toUpperCase().endsWith("PPTX")&&
				    	!fileName.toUpperCase().endsWith("TXT")&&!fileName.toUpperCase().endsWith("XLS")&&!fileName.toUpperCase().endsWith("XLSX")&&!fileName.toUpperCase().endsWith("PPT")&&
				    	!fileName.toUpperCase().endsWith("BMP")&&!fileName.toUpperCase().endsWith("GIF")&&!fileName.toUpperCase().endsWith("JPEG")&&
						   !fileName.toUpperCase().endsWith("PSD")&&!fileName.toUpperCase().endsWith("PNG")&&!fileName.toUpperCase().endsWith("RAW")&&!fileName.toUpperCase().endsWith("JPG")){
					json.put("result", "1");
					json.put("description", "上传失败，文件格式错误");
					return buildReqJsonObject(json);
				}*/
				
				String[] types = fileName.split("\\.");
				String type = types[types.length-1];
				
				String attachmentUuid = this.generateFileName(fileName);
				
				// 创建OSSClient实例
				OSSClient ossClient = new OSSClient(this.getEndPoint(), this.getAccessKeyId(), this.getAccessKeySecret());
				// 上传文件
				ObjectMetadata	metadata = new ObjectMetadata();
				
				metadata.setContentType(file.getContentType());
				
				PutObjectResult result = ossClient.putObject(this.getBucketName(), attachmentUuid+"."+type, 
						file.getInputStream(), metadata);
//				PutObjectResult result = ossClient.putObject("fastask", "testKeyForImg1", file.getBytes());
//				PutObjectResult result = ossClient.putObject("fastask", "testKeyForImg1", new File(filePath));

				// 关闭client
				ossClient.shutdown();
				
				AttachmentFileInfo attachment = new AttachmentFileInfo();
				attachment.setBucketname(this.getBucketName());
				attachment.setCreateDatetime(new Date());
				attachment.setFileName(fileName);
				attachment.setFileType("图片");
				attachment.setFileUuid(attachmentUuid);
				attachment.setUploadUuid("");
				attachment.setFileUrl("http://living.cto1024.com/"+attachmentUuid+"."+type);
				attachments.insertSelective(attachment);
				
				
			   /*Image img = null;
			   try {
					img = Image.getInstance(new URL("http://living.cto1024.com/"+attachmentUuid+"."+type));
				} catch (BadElementException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
		        	  System.out.println("img.width="+img.width()+" img.hight="+img.height());  */
				json.put("result", "0");
				json.put("description", "上传成功");
				json.put("attachment_uuid", attachmentUuid);
				json.put("attachment_url", "http://living.cto1024.com/"+attachmentUuid+"."+type);
				/*json.put("width", img.width());
				json.put("hight", img.height());*/
				return buildReqJsonObject(json);
			} catch (Exception e) {
				e.printStackTrace();
				json.put("result", "1");
				json.put("description", "上传失败，上传文件时发生未知错误");
				return buildReqJsonObject(json);
			}
		}else{
			json.put("result", "1");
			json.put("description", "上传失败，未获取到文件信息");
			return buildReqJsonObject(json);
		}
	}
	
	@RequestMapping(value ="media/audio_upload", method = RequestMethod.POST)
	public String audioUpload(HttpServletRequest request, @RequestParam("user_uuid") String user_uuid,@RequestParam("password") String password,@RequestParam("file") MultipartFile file) {
		
		JSONObject json = new JSONObject();
		if(user_uuid==null || password == null){
			json.put("result", "1");
			json.put("description", "请检查参数是否完整");
			return buildReqJsonObject(json);
		}

		// 判断文件是否为空
		if (!file.isEmpty()) {
			try {
				//校验文件类型  
				String contentType = file.getContentType();
				/*if(!"application/pdf".equals(contentType)&&!"application/msword".equals(contentType)
						&&!"text/plain".equals(contentType)&&!"application/vnd.ms-excel".equals(contentType)
						&&!"application/vnd.ms-powerpoint".equals(contentType)&&!"application/excel".equals(contentType)){
					json.put("result", "1");
					json.put("description", "请检查数据类型是否正确");
					return buildReqJsonObject(json);
				}*/
				String fileName = file.getOriginalFilename();
				/*if(!fileName.toUpperCase().endsWith("PDF")&&!fileName.toUpperCase().endsWith("DOC")&&!fileName.toUpperCase().endsWith("DOCX")&&!fileName.toUpperCase().endsWith("PPTX")&&
				    	!fileName.toUpperCase().endsWith("TXT")&&!fileName.toUpperCase().endsWith("XLS")&&!fileName.toUpperCase().endsWith("XLSX")&&!fileName.toUpperCase().endsWith("PPT")&&
				    	!fileName.toUpperCase().endsWith("BMP")&&!fileName.toUpperCase().endsWith("GIF")&&!fileName.toUpperCase().endsWith("JPEG")&&
						   !fileName.toUpperCase().endsWith("PSD")&&!fileName.toUpperCase().endsWith("PNG")&&!fileName.toUpperCase().endsWith("RAW")&&!fileName.toUpperCase().endsWith("JPG")){
					json.put("result", "1");
					json.put("description", "上传失败，文件格式错误");
					return buildReqJsonObject(json);
				}*/
				
				String[] types = fileName.split("\\.");
				String type = types[types.length-1];
				
				String attachmentUuid = this.generateFileName(fileName);
				
				// 创建OSSClient实例
				OSSClient ossClient = new OSSClient(this.getEndPoint(), this.getAccessKeyId(), this.getAccessKeySecret());
				// 上传文件
				ObjectMetadata	metadata = new ObjectMetadata();
				
				metadata.setContentType(file.getContentType());
				
				PutObjectResult result = ossClient.putObject(this.getBucketName1(), attachmentUuid+"."+type, 
						file.getInputStream(), metadata);
//				PutObjectResult result = ossClient.putObject("fastask", "testKeyForImg1", file.getBytes());
//				PutObjectResult result = ossClient.putObject("fastask", "testKeyForImg1", new File(filePath));

				// 关闭client
				ossClient.shutdown();
				
				AttachmentFileInfo attachment = new AttachmentFileInfo();
				attachment.setBucketname(this.getBucketName1());
				attachment.setCreateDatetime(new Date());
				attachment.setFileName(fileName);
				attachment.setFileType("音频");
				attachment.setFileUuid(attachmentUuid);
				attachment.setUploadUuid("");
				attachment.setFileUrl("http://living.cto1024.com/"+attachmentUuid+"."+type);
				String outputFileOSSUrl = MtsUtil.tranAudio(attachmentUuid+"."+type);
				attachment.setSizes(outputFileOSSUrl);
				attachments.insertSelective(attachment);
				
				json.put("result", "0");
				json.put("description", "上传成功");
				json.put("attachment_uuid", attachmentUuid);
				json.put("attachment_url", "http://living.cto1024.com/"+attachmentUuid+"."+type);
				json.put("outputFileOSSUrl", outputFileOSSUrl);
				return buildReqJsonObject(json);
			} catch (Exception e) {
				e.printStackTrace();
				json.put("result", "1");
				json.put("description", "上传失败，上传文件时发生未知错误");
				return buildReqJsonObject(json);
			}
		}else{
			json.put("result", "1");
			json.put("description", "上传失败，未获取到文件信息");
			return buildReqJsonObject(json);
		}
	}
	
	@RequestMapping(value ="media/video_upload", method = RequestMethod.POST)
	public String videoUpload(HttpServletRequest request, @RequestParam("user_uuid") String user_uuid,@RequestParam("password") String password,@RequestParam("file") MultipartFile file) {
		
		JSONObject json = new JSONObject();
		if(user_uuid==null || password == null){
			json.put("result", "1");
			json.put("description", "请检查参数是否完整");
			return buildReqJsonObject(json);
		}

		// 判断文件是否为空
		if (!file.isEmpty()) {
			try {
				//校验文件类型  
				String contentType = file.getContentType();
				/*if(!"application/pdf".equals(contentType)&&!"application/msword".equals(contentType)
						&&!"text/plain".equals(contentType)&&!"application/vnd.ms-excel".equals(contentType)
						&&!"application/vnd.ms-powerpoint".equals(contentType)&&!"application/excel".equals(contentType)){
					json.put("result", "1");
					json.put("description", "请检查数据类型是否正确");
					return buildReqJsonObject(json);
				}*/
				String fileName = file.getOriginalFilename();
				/*if(!fileName.toUpperCase().endsWith("PDF")&&!fileName.toUpperCase().endsWith("DOC")&&!fileName.toUpperCase().endsWith("DOCX")&&!fileName.toUpperCase().endsWith("PPTX")&&
				    	!fileName.toUpperCase().endsWith("TXT")&&!fileName.toUpperCase().endsWith("XLS")&&!fileName.toUpperCase().endsWith("XLSX")&&!fileName.toUpperCase().endsWith("PPT")&&
				    	!fileName.toUpperCase().endsWith("BMP")&&!fileName.toUpperCase().endsWith("GIF")&&!fileName.toUpperCase().endsWith("JPEG")&&
						   !fileName.toUpperCase().endsWith("PSD")&&!fileName.toUpperCase().endsWith("PNG")&&!fileName.toUpperCase().endsWith("RAW")&&!fileName.toUpperCase().endsWith("JPG")){
					json.put("result", "1");
					json.put("description", "上传失败，文件格式错误");
					return buildReqJsonObject(json);
				}*/
				
				String[] types = fileName.split("\\.");
				String type = types[types.length-1];
				
				String attachmentUuid = this.generateFileName(fileName);
				
				// 创建OSSClient实例
				OSSClient ossClient = new OSSClient(this.getEndPoint(), this.getAccessKeyId(), this.getAccessKeySecret());
				// 上传文件
				ObjectMetadata	metadata = new ObjectMetadata();
				
				metadata.setContentType(file.getContentType());
				
				PutObjectResult result = ossClient.putObject(this.getBucketName2(), attachmentUuid+"."+type, 
						file.getInputStream(), metadata);
//				PutObjectResult result = ossClient.putObject("fastask", "testKeyForImg1", file.getBytes());
//				PutObjectResult result = ossClient.putObject("fastask", "testKeyForImg1", new File(filePath));

				// 关闭client
				ossClient.shutdown();
				
				AttachmentFileInfo attachment = new AttachmentFileInfo();
				attachment.setBucketname(this.getBucketName2());
				attachment.setCreateDatetime(new Date());
				attachment.setFileName(fileName);
				attachment.setFileType("小视频");
				attachment.setFileUuid(attachmentUuid);
				attachment.setUploadUuid("");
				attachment.setFileUrl("http://living.cto1024.com/"+attachmentUuid+"."+type);
				//String outputFileOSSUrl = MtsUtil.tranAudio(attachmentUuid+"."+type);
				attachment.setSizes("http://living.cto1024.com/"+attachmentUuid+"."+type);
				attachments.insertSelective(attachment);
				
				json.put("result", "0");
				json.put("description", "上传成功");
				json.put("attachment_uuid", attachmentUuid);
				json.put("attachment_url", "http://living.cto1024.com/"+attachmentUuid+"."+type);
				return buildReqJsonObject(json);
			} catch (Exception e) {
				e.printStackTrace();
				json.put("result", "1");
				json.put("description", "上传失败，上传文件时发生未知错误");
				return buildReqJsonObject(json);
			}
		}else{
			json.put("result", "1");
			json.put("description", "上传失败，未获取到文件信息");
			return buildReqJsonObject(json);
		}
	}
	
private String generateFileName(String originalName) {
		
		try {
			return MD5Util.md5Digest(originalName + String.valueOf(Calendar.getInstance().getTimeInMillis()) + RandomStringUtils.random(8));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	protected String buildReqJsonObject(Object msgs) {
		 JSONObject jo = new JSONObject();
		 jo.put("head", getDefaultOutHead());
		 jo.put("body", msgs);
		 return jo.toString();
	 }
	
	protected OutHead getDefaultOutHead() {
		 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			String time = format.format(date);
	        return new OutHead()
	                .setReturnCode("000")
	                .setReturnDescription("验证通过")
	                .setSysTime(time);
	 }
}
