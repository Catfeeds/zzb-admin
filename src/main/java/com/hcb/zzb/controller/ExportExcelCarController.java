package com.hcb.zzb.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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

import com.hcb.zzb.controller.base.BaseControllers;
import com.hcb.zzb.dto.Car;
import com.hcb.zzb.dto.export.CarExport;
import com.hcb.zzb.service.ICarSevice;

import net.sf.json.JSONObject;
@Controller
public class ExportExcelCarController<T> extends BaseControllers{
	@Autowired
	ICarSevice carService;
	/**
	 * 导出车辆列表
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 */
	@RequestMapping(value="exportExcelCar",method=RequestMethod.POST)
	@ResponseBody
	public String exportExcelCar(HttpServletRequest req, 
			HttpServletResponse res,ModelMap model) {
		JSONObject json=new JSONObject();
		if(sign==1||sign==2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		JSONObject bodyInfo=JSONObject.fromObject(bodyString);
		if(bodyInfo.get("pageIndex")==null||bodyInfo.get("pageSize")==null) {
			json.put("result", "1");
			json.put("description", "请检查参数是否完整");
			return buildReqJsonObject(json);
		}
		if("".equals(bodyInfo.get("pageIndex"))||"".equals(bodyInfo.get("pageSize"))) {
			json.put("result", "1");
			json.put("description", "请检查参数是否正确");
			return buildReqJsonObject(json);
		}
		Integer pageIndex=bodyInfo.getInt("pageIndex");
		Integer pageSize=bodyInfo.getInt("pageSize");
		Integer start=(pageIndex-1)*pageSize;
		if (pageIndex <= 0) {
			json.put("result", "1");
			json.put("description", "pageIndex不能小于0");
			return buildReqJsonObject(json);
		}
		Map<String, Object> map=new HashMap<>();
		map.put("start", start);
		map.put("end", pageSize);
		if(bodyInfo.get("modelCharacter")!=null&&!"".equals(bodyInfo.get("modelCharacter"))) {
			map.put("modelCharacter", bodyInfo.getString("modelCharacter"));
		}
		int count=carService.countSelectByMapLimit(map);
		int total = count % pageSize == 0 ? count/pageSize:count/pageSize+1;
		if(pageIndex > total) {
			json.put("result", "1");
			json.put("description", "操作失败,pageIndex大于总页数");
			return buildReqJsonObject(json);
		}
		List<Car> list=new ArrayList<>();
		list = carService.selectByMapLimit(map);
		List<CarExport> exportList=new ArrayList<>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		int i=1;
		for (Car car : list) {
			CarExport carEx=new CarExport();
			carEx.setSerialNumber(i);
			carEx.setCarId(car.getId());
			carEx.setModelCharacter(car.getModelCharacter()==null?"":car.getModelCharacter());
			carEx.setOrderQuantity(car.getOrderQuantity()==null?0:car.getOrderQuantity());
			carEx.setTicketQuantity(0);
			carEx.setStatus("");
			
			exportList.add(carEx);
			i++;
		}
		
		ExportExcelCarController<CarExport> ex=new ExportExcelCarController<CarExport>();
		String[] headers =  { "序号", "车辆ID", "车型", "接单量", "罚单数量", "状态"};
		try  
        {         
    		Date date = new Date();
    		String fileName = format.format(date);
    		
    		String path = "/opt/avater/"+fileName+".xls";
            OutputStream out = new FileOutputStream(path);   
            ex.exportExcel(headers, exportList, out);  
            out.close();   
            File file = new File(path);  
            // 取得文件名。  
            String filename = file.getName();  
            // 以流的形式下载文件。  
            InputStream fis = new BufferedInputStream(new FileInputStream(path));  
            byte[] buffer = new byte[fis.available()];  
            fis.read(buffer);  
            fis.close();  
            // 清空response  
            res.reset();  
            // 设置response的Header  
            res.addHeader("Content-Disposition", "attachment;filename="  
                    + new String(filename.getBytes()));  
            res.addHeader("Content-Length", "" + file.length());  
            OutputStream toClient = new BufferedOutputStream(  
            		res.getOutputStream());  
            res.setContentType("application/vnd.ms-excel;charset=gb2312");  
            toClient.write(buffer);  
            toClient.flush();  
            toClient.close(); 
        } catch (FileNotFoundException e) {  
        	json.put("result", "1");
			json.put("description", e.getMessage());
			return buildReqJsonObject(json);
        } catch (IOException e) {  
        	json.put("result", "1");
			json.put("description", e.getMessage());
			return buildReqJsonObject(json);
        }
		return "";
	}
	
	
	
	public void exportExcel(Collection<T> dataset, OutputStream out)  
    {  
        exportExcel("车辆列表", null, dataset, out, "yyyy-MM-dd");  
    }  
  
    public void exportExcel(String[] headers, Collection<T> dataset,  
            OutputStream out)  
    {  
        exportExcel("车辆列表", headers, dataset, out, "yyyy-MM-dd");  
    }  
  
    public void exportExcel(String[] headers, Collection<T> dataset,  
            OutputStream out, String pattern)  
    {  
        exportExcel("车辆列表", headers, dataset, out, pattern);  
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
}
