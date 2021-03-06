package com.hcb.zzb.controller;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.hcb.zzb.controller.base.BaseControllers;
import com.hcb.zzb.dto.Orders;
import com.hcb.zzb.dto.Ticket;
import com.hcb.zzb.dto.Users;
import com.hcb.zzb.dto.export.TicketExport;
import com.hcb.zzb.service.IOrderService;
import com.hcb.zzb.service.ITicketService;
import com.hcb.zzb.service.IUsersService;
import com.hcb.zzb.util.Config;

import net.sf.json.JSONObject;
@Controller
public class ExportExcelTicketController<T> extends BaseControllers{
	@Autowired
	ITicketService ticketService;
	@Autowired
	IUsersService userService;
	@Autowired
	IOrderService orderService;
	
	
	/**
	 * 导出罚单列表Excel
	 * @return
	 */
	@RequestMapping(value="exportExcelTicket",method=RequestMethod.POST)
	@ResponseBody
	public String exportExcelTicket(HttpServletRequest req, 
			HttpServletResponse res,ModelMap model) {
		JSONObject json=new JSONObject();
		if(sign==1||sign==2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		
		Integer start=0;
		Map<String, Object> map=new HashMap<>();
		int count = ticketService.countSelectTickets(map);
		if(count==0) {
			json.put("result", "1");
			json.put("description", "没有数据");
			return buildReqJsonObject(json);
		}
		map.put("start", start);
		map.put("end", count);	
		map.put("orderBy", 2);
		List<Map<String, Object>> list=new ArrayList<>();
		list=ticketService.selectTicketsLimit(map);
		for (Map<String, Object> map2 : list) {
			String userUuid=(String)map2.get("userUuid");
			String ordernumber=(String)map2.get("orderNumber");
			Orders orders= orderService.selectByordernumber(ordernumber);
			if(orders!=null){
				String carOwnerUuid = orders.getCarOwnerUuid();
				Users userOwner = userService.selectByUserUuid(carOwnerUuid);
				map2.put("userOwner", userOwner);
			}else{
				map2.put("userOwner", "");
			}
			Users user = userService.selectByUserUuid(userUuid);
			if(user!=null){
				map2.put("user", user);
			}else{
				map2.put("user", "");
			}
		}
		List<TicketExport> exportList=new ArrayList<>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		int i=1;
		for (Map<String, Object> ticket : list) {
			TicketExport ticketExp=new TicketExport();
			ticketExp.setSerialNumber(i);
			ticketExp.setOrderNumber(ticket.get("orderNumber")==null?"":ticket.get("orderNumber").toString());
			Users user = userService.selectByUserUuid(ticket.get("userUuid").toString());
			if(user!=null) {
				ticketExp.setUserName(user.getUserName()==null?"":user.getUserName());
			}else {
				ticketExp.setUserName("");
			}
			if(ticket.get("userOwner")!=null&&!ticket.get("userOwner").equals("")){
				Users carOwner=(Users) ticket.get("userOwner");
				if(carOwner!=null){
					ticketExp.setCarOwner(carOwner.getUserName()==null?"":carOwner.getUserName());
				}else{
					ticketExp.setCarOwner("");
				}
			}else{
				ticketExp.setCarOwner("");
			}
			
			
			//ticketExp.setAddress(ticket.get("address")==null?"":ticket.get("address").toString());
			ticketExp.setAddress(ticket.get("illegalCode")==null?"":ticket.get("illegalCode").toString());
			ticketExp.setMoney(ticket.get("money")==null?"0":ticket.get("money").toString());
			ticketExp.setPoints(ticket.get("points")==null?0:Integer.parseInt(ticket.get("points").toString()));
			//处理方式 1：自负 2：委托
			if(ticket.get("dealway")!=null){
				if(ticket.get("dealway").equals(1)){
					ticketExp.setDisposeWay("自负");
				}else if(ticket.get("dealway").equals(2)){
					ticketExp.setDisposeWay("委托");
				}else{
					ticketExp.setDisposeWay("");
				}
			}else{
				ticketExp.setDisposeWay("");
			}
			//ticketExp.setTime(ticket.get("illegalTime")==null?"":format.format(ticket.get("illegalTime")));
		
			exportList.add(ticketExp);
			i++;
		}
		
		ExportExcelTicketController<TicketExport> ex=new ExportExcelTicketController<TicketExport>();
	  //String[] headers =  { "序号", "订单号", "用户","车主","违章信息","罚款","扣分","处理方式","时间"};
		String[] headers =  { "序号", "订单号", "用户","车主","违章信息","罚款","扣分","处理方式",};
		String avatar = "";
		try  
	        {         
	    		Date date = new Date();
	    		String fileName = format.format(date);
	    		
	    		String path = "/opt/avater/"+fileName+".xls";
	    		//String path = "E:/"+fileName+".xls";
	            OutputStream out = new FileOutputStream(path);   
	            ex.exportExcel(headers, exportList, out);  
	            out.close();   
	            File file = new File(path);  
	           
	            OSSClient ossClient = new OSSClient(this.getEndPoint(), this.getAccessKeyId(), this.getAccessKeySecret());
				// 上传文件
				ObjectMetadata	metadata = new ObjectMetadata();
				metadata.setContentType("xls");
				ossClient.putObject(this.getBucketName(), fileName+".xls", 
						file, metadata);
				// 关闭client
				ossClient.shutdown(); 
				avatar = "http://"+this.getBucketName()+".oss-cn-hangzhou.aliyuncs.com/"+fileName+".xls";
	       
	        } catch (FileNotFoundException e) {  
	        	json.put("result", "1");
				json.put("description", e.getMessage());
				return buildReqJsonObject(json);
	        } catch (IOException e) {  
	        	json.put("result", "1");
				json.put("description", e.getMessage());
				return buildReqJsonObject(json);
	        }
		 json.put("result", "0");
		 json.put("description", "导出成功");
		 json.put("fileUrl", avatar);
		 return buildReqJsonObject(json);
	}
	
	
	public void exportExcel(Collection<T> dataset, OutputStream out)  
    {  
        exportExcel("罚单列表", null, dataset, out, "yyyy-MM-dd");  
    }  
  
    public void exportExcel(String[] headers, Collection<T> dataset,  
            OutputStream out)  
    {  
        exportExcel("罚单列表", headers, dataset, out, "yyyy-MM-dd");  
    }  
  
    public void exportExcel(String[] headers, Collection<T> dataset,  
            OutputStream out, String pattern)  
    {  
        exportExcel("罚单列表", headers, dataset, out, pattern);  
    }  
	
	
	   /** 
     * 这是一个通用的方法，利用了JAVA的反射机制，可以将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出到指定IO设备上 
     *  
     * @param title 
     *            表格标题名 
     * @param headers 
     *            表格属性列名数组 
     * @param dataset 
     *            需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的 
     *            javabean属性的数据类型有基本数据类型及String,Date,byte[](图片数据) 
     * @param out 
     *            与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中 
     * @param pattern 
     *            如果有时间数据，设定输出格式。默认为"yyy-MM-dd" 
     */  
    @SuppressWarnings("unchecked")  
    public void exportExcel(String title, String[] headers,  
            Collection<T> dataset, OutputStream out, String pattern)  
    {  
        // 声明一个工作薄  
        HSSFWorkbook workbook = new HSSFWorkbook();  
        // 生成一个表格  
        HSSFSheet sheet = workbook.createSheet(title);  
        // 设置表格默认列宽度为15个字节  
        //sheet.setDefaultColumnWidth((short) 15);  序号 	消费项目编码 	消费项目名称 	消费场馆 	消费者 	状态 	描述 	备注 	消费金额 	支付方式 	消费时间
        sheet.setColumnWidth(0, 8*256);
        sheet.setColumnWidth(1, 40*256);
        
        sheet.setColumnWidth(2, 25*256);
        sheet.setColumnWidth(3, 15*256);
        sheet.setColumnWidth(4, 15*256);
        sheet.setColumnWidth(5, 20*256);
        sheet.setColumnWidth(6, 20*256);
        
        sheet.setColumnWidth(7, 20*256);
        sheet.setColumnWidth(8, 15*256);
        sheet.setColumnWidth(9, 15*256);
        sheet.setColumnWidth(10, 20*256);
        sheet.setColumnWidth(11, 15*256);
        sheet.setColumnWidth(12, 15*256);
        sheet.setColumnWidth(13, 15*256);
        // 生成一个样式  
        HSSFCellStyle style = workbook.createCellStyle();  
        // 设置这些样式  
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);  
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);  
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
        // 生成一个字体  
        HSSFFont font = workbook.createFont();  
        font.setColor(HSSFColor.BLACK.index);  
        font.setFontHeightInPoints((short) 12);  
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); 
        font.setFontName("Times");
        // 把字体应用到当前的样式  
        style.setFont(font);  
        // 生成并设置另一个样式  
        HSSFCellStyle style2 = workbook.createCellStyle();  
        style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);  
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);  
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);  
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
        // 生成另一个字体  
        HSSFFont font2 = workbook.createFont();  
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);  
        font2.setFontName("Times");
        // 把字体应用到当前的样式  
        style2.setFont(font2);  
  
        // 声明一个画图的顶级管理器  
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();  
        // 定义注释的大小和位置,详见文档  
        HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0,  
                0, 0, 0, (short) 4, 2, (short) 6, 5));  
        // 设置注释内容  
        comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));  
        // 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.  
        comment.setAuthor("leno");  
  
        // 产生表格标题行  
        HSSFRow row = sheet.createRow(0);  
        for (short i = 0; i < headers.length; i++)  
        {  
            HSSFCell cell = row.createCell(i);  
            cell.setCellStyle(style);  
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);  
            cell.setCellValue(text);  
        }  
  
        // 遍历集合数据，产生数据行  
        Iterator<T> it = dataset.iterator();  
        int index = 0;  
        while (it.hasNext())  
        {  
            index++;  
            row = sheet.createRow(index);  
            T t = (T) it.next();  
            // 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值  
            Field[] fields = t.getClass().getDeclaredFields();  
            for (short i = 0; i < fields.length; i++)  
            {  
                HSSFCell cell = row.createCell(i);  
                cell.setCellStyle(style2);  
                Field field = fields[i];  
                String fieldName = field.getName();  
                String getMethodName = "get"  
                        + fieldName.substring(0, 1).toUpperCase()  
                        + fieldName.substring(1);  
                try  
                {  
                    Class tCls = t.getClass();  
                    Method getMethod = tCls.getMethod(getMethodName,  
                            new Class[]  
                            {});  
                    Object value = getMethod.invoke(t, new Object[]  
                    {});  
                    // 判断值的类型后进行强制类型转换  
                    String textValue = null;  
                    if(value==null){
                    	 textValue = ""; 
                    }else{
                    	 textValue = value.toString(); 
                    }
                    // 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成  
                    if (textValue != null)  
                    {  
                        Pattern p = Pattern.compile("^//d+(//.//d+)?$");  
                        Matcher matcher = p.matcher(textValue);  
                        if (matcher.matches())  
                        {  
                            // 是数字当作double处理  
                            cell.setCellValue(Double.parseDouble(textValue));  
                        }  
                        else  
                        {  
                            HSSFRichTextString richString = new HSSFRichTextString(  
                                    textValue);  
                            HSSFFont font3 = workbook.createFont();  
                            font3.setColor(HSSFColor.BLACK.index);  
                            richString.applyFont(font3);  
                            cell.setCellValue(richString);  
                        }  
                    }  
                }  
                catch (Exception e)  
                {  
                    e.printStackTrace();  
                }  
                finally  
                {  
                    // 清理资源  
                }  
            }  
        }  
        try  
        {  
            workbook.write(out);  
        }  
        catch (IOException e)  
        {  
            e.printStackTrace();  
        }  
    } 
    
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
}
