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
import com.hcb.zzb.dto.Car;
import com.hcb.zzb.dto.FinanceRecord;
import com.hcb.zzb.dto.Orders;
import com.hcb.zzb.dto.Users;
import com.hcb.zzb.dto.platformPo;
import com.hcb.zzb.dto.export.PlatformExport;
import com.hcb.zzb.service.ICarSevice;
import com.hcb.zzb.service.IFinanceRecordService;
import com.hcb.zzb.service.IOrderService;
import com.hcb.zzb.service.IPlatformConfigService;
import com.hcb.zzb.service.IUsersService;
import com.hcb.zzb.util.Config;

import net.sf.json.JSONObject;
@Controller
public class ExportExcelPlatform<T> extends BaseControllers{
	@Autowired
	IFinanceRecordService financeRecordService;
	@Autowired
	IPlatformConfigService platformConfigService;
	@Autowired
	IOrderService orderService;
	@Autowired
	IUsersService usersService;
	@Autowired
	private ICarSevice carService;
	
	/**
	 * 导出平台账户列表
	 * @return
	 */
	@RequestMapping(value="exportExcelPlatform",method=RequestMethod.POST)
	@ResponseBody
	public String exports(){
		JSONObject json=new JSONObject();
		if(sign==1||sign==2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		
		Map<String, Object> map=new HashMap<>();
		map.put("start", 0);
		map.put("recordType", 4);
		map.put("orderBy", 2);
		int count = financeRecordService.countSelectByRecordType(map);
		map.put("end", count);
		
		
		if(count ==0) {
			json.put("result", "1");
			json.put("description", "未查询到平台账户的收支明细");
			return buildReqJsonObject(json);
		}
	
	
		
		ModelMap model=new ModelMap();
		//总营收（历史成功订单总数）
		Float totalmoney =orderService.selectMoney();
		model.put("totalmoney", 0 * totalmoney);
		//历史新高（日期-金额）
		Float highmoney =orderService.selectHighMoney();
		
		model.put("highmoney", 0 * highmoney);
		//押金池，
		Float poolmoney =orderService.selectPoolMoney();
		model.put("poolmoney", poolmoney);
		//平台账户收支明细列表
		//订单状态（预定成功，服务中，已还车，已结案）
		//租客/车东/车款/用车时间/付款时间/
		//付款渠道/付款金额/押金金额/城市/
		List<FinanceRecord> financeList=new ArrayList<>();
		financeList=financeRecordService.selectByRecordType(map);
		
		//Map<String, Object>mapp1=new HashMap<String, Object>();
		//mapp1.put("platformList", financeList);
		if(!financeList.isEmpty()) {
			
			//平台账户今日收入
			Map<String, Object> tmap=new HashMap<>();
			tmap.put("recordType", 4);//记录类型；1：充值；2：提现；3：订单；4：平台收费
			tmap.put("financeType", 1);//1收入 2支出
			List<FinanceRecord> todayIncomeList= financeRecordService.selectIncomeAndExpenditureByToday(tmap);
			if(todayIncomeList!=null&&!todayIncomeList.isEmpty()) {
				float todayIncomeTotal=0;
				for (FinanceRecord financeRecord : todayIncomeList) {
					todayIncomeTotal= todayIncomeTotal + financeRecord.getMoney();
				}
				
				model.put("income", (float)(Math.round(todayIncomeTotal*100))/100);
			}else {
				model.put("income", 0);
			}	
			//平台账户今日支出
			Map<String, Object> tmap1=new HashMap<>();
			tmap1.put("recordType", 4);//记录类型；1：充值；2：提现；3：订单；4：平台收费
			tmap1.put("financeType", 2);//1收入 2支出
			List<FinanceRecord> todayExpenditureList= financeRecordService.selectIncomeAndExpenditureByToday(tmap1);
			if(todayExpenditureList!=null&&!todayExpenditureList.isEmpty()) {
				float todayExpenditureTotal = 0;
				for (FinanceRecord financeRecord : todayExpenditureList) {
					todayExpenditureTotal = todayExpenditureTotal + financeRecord.getMoney();
				}
				model.put("expenditure", (float)(Math.round(todayExpenditureTotal*100))/100);
				
			}else {
				model.put("expenditure", 0);
			}
			
			/*PlatformConfig platform = platformConfigService.selectByPrimaryKey(39);
			if(platform==null) {
				json.put("result", "1");
				json.put("description", "没有平台账号");
				return buildReqJsonObject(json);
			}
			model.put("balance", platform.getBalance()==null?0:platform.getBalance());*/
			//model.put("platformList", financeList);
			
			
			
		}
		
		//列表明细
		//List<Map<String, Object>> listlist=new ArrayList<Map<String, Object>>();
		//listlist.add(mapp1);
		
		if(financeList.size()>0){
			for (FinanceRecord financeRecord : financeList) {
				//Map<String, Object>map2=new HashMap<String, Object>();
				if(financeRecord!=null&&financeRecord.getOrderUuid()!=null){
					String orderUuid=financeRecord.getOrderUuid();
					Orders order = orderService.selectByOrdersUuid(orderUuid);
					if(order!=null){
						platformPo newpo=new platformPo();
						//int orderStatus =order.getOrderStatus();
						String userUuid=order.getUserUuid();
						String ownerUuid=order.getCarOwnerUuid();
						String carUuid=order.getCarUuid();
						Users user = usersService.selectByUserUuid(userUuid);
						Users userOwner = usersService.selectByUserOwnerUuid(ownerUuid);
						if(user!=null){
							//json.put("user", user);
							//map2.put("user", user);
							newpo.setUserName(user.getUserName());
							newpo.setPhone(user.getUserPhone());
						}else{
							//json.put("user", "");
							//map2.put("user", user);
							newpo.setUserName("");
							newpo.setPhone("");
						}
						if(userOwner!=null){
							//json.put("userOwner", userOwner);
							//map2.put("userOwner", userOwner);
							newpo.setOwnerName(userOwner.getUserName());
							newpo.setOwnerPhone(userOwner.getUserPhone());
						}else{
							//json.put("userOwner", "");
							//map2.put("userOwner", "");
							newpo.setOwnerName("");
							newpo.setOwnerPhone("");
						}
						Car car = carService.selectByUuid(carUuid);
						if(car!=null){
							//车款/用车时间/付款时间 /付款渠道/付款金额/押金金额/城市/订单链接
							//json.put("carBrand", car.getBrand());
							//json.put("city", car.getCity());
							//map2.put("carBrand", car.getBrand());
							//map2.put("city", car.getCity());
							newpo.setCarBrand(car.getBrand());
							newpo.setCity(car.getCity());
						}
						//（预定成功，服务中，已还车，已结案）
						//if(orderStatus==3){}
						//json.put("orderNumber", order.getOrderNumber()==null?"":order.getOrderNumber());
						//json.put("orderStatus", order.getOrderStatus()==null?"":order.getOrderStatus());
						//json.put("takeCarTime", DateUtil.getDate(order.getTakeCarTime()));
						//json.put("payTime", DateUtil.getDate(order.getPayTime()));
						//json.put("payType", order.getPayType()==null?1:order.getPayType());
						//json.put("totalPrice", String.valueOf(order.getTotalPrice()==null?"":order.getTotalPrice()));
						//json.put("deposit", String.valueOf(order.getDeposit()==null?"":order.getDeposit()));
						/*map2.put("orderNumber", order.getOrderNumber()==null?"":order.getOrderNumber());
						map2.put("orderStatus", order.getOrderStatus()==null?"":order.getOrderStatus());
						map2.put("takeCarTime", DateUtil.getDate(order.getTakeCarTime()));
						map2.put("payTime", DateUtil.getDate(order.getPayTime()));
						map2.put("payType", order.getPayType()==null?1:order.getPayType());
						map2.put("totalPrice", String.valueOf(order.getTotalPrice()==null?"":order.getTotalPrice()));
						map2.put("deposit", String.valueOf(order.getDeposit()==null?"":order.getDeposit()));*/
						newpo.setOrderNumber(order.getOrderNumber()==null?"":order.getOrderNumber());
						newpo.setOrderStatus(order.getOrderStatus()==null?0:order.getOrderStatus());
						newpo.setTakeCarTime(order.getTakeCarTime());
						newpo.setPayTime(order.getPayTime());
						newpo.setPayType(order.getPayType()==null?1:order.getPayType());
						newpo.setTotalPrice(order.getTotalPrice()==null?0f:order.getTotalPrice());
						newpo.setDeposit(order.getDeposit()==null?0f:order.getDeposit());
						
						financeRecord.setPlat(newpo);
					}
					//listlist.add(map2);
				}
			}
		}
		
		
		List<PlatformExport> exportList=new ArrayList<>();
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		int i=1;
		
		for (FinanceRecord financeRecord : financeList) {
			PlatformExport pe=new PlatformExport();
			pe.setSerialNumber(i);
			if(financeRecord.getPlat()!=null){
				pe.setOrderNumber(financeRecord.getPlat().getOrderNumber()==null?"":financeRecord.getPlat().getOrderNumber());
				//订单状态：1：未接单；2：拒绝接单；3：接单未取车；4：接单已取车；5：确认还车未收车；6：已收车；7：已取消； 8：已退款；
				if(financeRecord.getPlat().getOrderStatus()==null||financeRecord.getPlat().getOrderStatus()==0){
					pe.setStatus("");
				}else if(financeRecord.getPlat().getOrderStatus()==1){
					pe.setStatus("未接单");
				}else if(financeRecord.getPlat().getOrderStatus()==2){
					pe.setStatus("拒绝接单");
				}else if(financeRecord.getPlat().getOrderStatus()==3){
					pe.setStatus("接单未取车");
				}else if(financeRecord.getPlat().getOrderStatus()==4){
					pe.setStatus("接单已取车");
				}else if(financeRecord.getPlat().getOrderStatus()==5){
					pe.setStatus("确认还车未收车");
				}else if(financeRecord.getPlat().getOrderStatus()==6){
					pe.setStatus("已收车");
				}else if(financeRecord.getPlat().getOrderStatus()==7){
					pe.setStatus("已取消");
				}else if(financeRecord.getPlat().getOrderStatus()==8){
					pe.setStatus("已退款");
				}else{
					pe.setStatus("");
				}
				pe.setZuKe(financeRecord.getPlat().getUserName()==null?"":financeRecord.getPlat().getUserName());
				pe.setCarOwner(financeRecord.getPlat().getOwnerName()==null?"":financeRecord.getPlat().getOwnerName());
				pe.setCarmodel(financeRecord.getPlat().getCarBrand()==null?"":financeRecord.getPlat().getCarBrand());
				pe.setUseCarTime(financeRecord.getPlat().getTakeCarTime()==null?"":format.format(financeRecord.getPlat().getTakeCarTime()));
				pe.setPayTime(financeRecord.getPlat().getPayTime()==null?"":format.format(financeRecord.getPlat().getPayTime()));
				pe.setChanel(financeRecord.getPlat().getPayType()==null?"":(financeRecord.getPlat().getPayType())+"");
				pe.setPayMoney(financeRecord.getPlat().getTotalPrice()==null?0:financeRecord.getPlat().getTotalPrice());
				pe.setDeposit(financeRecord.getPlat().getDeposit()==null?0:financeRecord.getPlat().getDeposit());
				pe.setCity(financeRecord.getPlat().getCity()==null?"":financeRecord.getPlat().getCity());
				pe.setDate(financeRecord.getPlat().getPayTime()==null?"":format.format(financeRecord.getPlat().getPayTime()));
			}else{
				pe.setOrderNumber("");
				pe.setStatus("");
				pe.setZuKe("");
				pe.setCarOwner("");
				pe.setCarmodel("");
				pe.setUseCarTime("");
				pe.setPayTime("");
				pe.setChanel("");
				pe.setPayMoney(0f);
				pe.setDeposit(0f);
				pe.setCity("");
				pe.setDate("");
			}
			
			exportList.add(pe);
			i++;
		}
		ExportExcelPlatform<PlatformExport> ex=new ExportExcelPlatform<PlatformExport>();
		String[] headers =  {"序号","订单号","订单状态","租客","车东","车款","用车时间","付款时间","付款渠道","付款金额","押金金额","城市","时间"};
		
		String avatar = "";
		try  
        {         
    		Date date = new Date();
    		String fileName = format.format(date);
    		
    		String path = "/opt/avater/"+fileName+".xls";
    		//String path="E:/"+fileName+".xls";
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
        exportExcel("平台账户", null, dataset, out, "yyyy-MM-dd");  
    }  
  
    public void exportExcel(String[] headers, Collection<T> dataset,  
            OutputStream out)  
    {  
        exportExcel("平台账户", headers, dataset, out, "yyyy-MM-dd");  
    }  
  
    public void exportExcel(String[] headers, Collection<T> dataset,  
            OutputStream out, String pattern)  
    {  
        exportExcel("平台账户", headers, dataset, out, pattern);  
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
