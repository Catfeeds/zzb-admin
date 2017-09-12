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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.hcb.zzb.controller.base.BaseControllers;
import com.hcb.zzb.dto.Car;
import com.hcb.zzb.dto.OwnerPo;
import com.hcb.zzb.dto.Users;
import com.hcb.zzb.dto.export.CarOwnerExport;
import com.hcb.zzb.service.ICarSevice;
import com.hcb.zzb.service.IOrderService;
import com.hcb.zzb.service.IUsersService;
import com.hcb.zzb.util.Config;

import net.sf.json.JSONObject;
/**
 * 导出车主excel
 * @author kk
 *
 * @param <T>
 */
@Controller
public class ExportCarOwnerController<T> extends BaseControllers {
	@Autowired
	private IUsersService usersService;
	@Autowired
	private ICarSevice carService;
	@Autowired
	private IOrderService orderService;
	@RequestMapping(value="exportCarOwnerList",method=RequestMethod.POST)
	@ResponseBody
	public String exportCarOwnerList(){
		JSONObject json=new JSONObject();
		if(sign==1||sign==2) {
			json.put("result", "1");
			json.put("description", "请检查参数格式是否正确或者参数是否完整");
			return buildReqJsonInteger(1, json);
		}
		Map<String, Object> map=new HashMap<String, Object>();
		int start=0;
		map.put("start", start);
		int count=usersService.countUsersOwnerByMap(map);
		
		if(count==0) {
			json.put("result", "1");
			json.put("description", "未查询到数据");
			return buildReqJsonObject(json);
		}
		map.put("end", count);
		map.put("orderBy", 2);
		List<Users> list=usersService.selectUsersOwnerByMap(map);
		//Map<String, Object> mapppp=new HashMap<>();
		//mapppp.put("list", list);
		//List<Map<String, Object>>list2=new ArrayList<Map<String, Object>>();
		//list2.add(mapppp);
		int carnum1=0;//有效车辆数
		int carnum2=0;//历史车辆数
		int sureordercount;//接单次数
		//Float money;//消费金额
		for (Users user : list) {
			//Map<String, Object> map2=new HashMap<>();
			if(user!=null){
				String userUuid=user.getUserUuid();
				if(userUuid!=null){
					sureordercount=orderService.selectSureOrder(userUuid);
					List<Car> car=carService.selectByCarBand(userUuid);
					carnum1=carService.selectNum1(userUuid);
					carnum2=carService.selectNum2(userUuid);
					 //订单GDP
					//平台分佣、
					 //差价利润
					 ///可提现金额
					 //提现中金额
					//已提现金额
					 OwnerPo ownerPo=new OwnerPo();
					 ownerPo.setAlreadybalance(0);
					 ownerPo.setAvg(0f);
					 ownerPo.setCarnum1(carnum1);
					 ownerPo.setCarnum2(carnum2);
					 ownerPo.setCars(car);
					 ownerPo.setCashbalance(0f);
					 ownerPo.setChajialirun(0f);
					 ownerPo.setGdp(0f);
					 ownerPo.setKetixianjiner(0f);
					 ownerPo.setSureordercount(sureordercount);
					 user.setOwnerPo(ownerPo);
				}else{
					 //map2.put("car", "");
					 //map2.put("carnum", 0);
					 //map2.put("historycarnum", 0);
					 //map2.put("sureordercount", 0);
					 //map2.put("GDP", 0);//订单GDP
					 //map2.put("avg", 0);//平台分佣、
					 //map2.put("chajialirun", 0);//差价利润
					 //map2.put("ketixianjiner", 0);///可提现金额
					 //map2.put("cashbalance", 0);//提现中金额
					 //map2.put("alreadybalance", 0);//已提现金额
				}
			}
		}
		
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		List<CarOwnerExport> exportList=new ArrayList<>();
		int i=1;
		for (Users users : list) {
			CarOwnerExport ce=new CarOwnerExport();
			ce.setSerialNumber(i);
			ce.setId(users.getId()==null?"0":users.getId()+"");
			ce.setName(users.getUserName()==null?"":users.getUserName());
			ce.setPhone(users.getUserPhone()==null?"":users.getUserPhone());
			if(users.getGender()!=null){
				if(users.getGender()==1){
					ce.setSex("男");
				}else if(users.getGender()==2){
					ce.setSex("女");
				}else{
					ce.setSex("");
				}
			}else{
				ce.setSex("");
			}
			OwnerPo owner= users.getOwnerPo();
			if(owner!=null){
				List<Car> carList=owner.getCars();
				String brands="";
				if(carList!=null&&!carList.isEmpty()){
					for (Car car2 : carList) {
						brands=brands+(car2.getBrand()==null?"":car2.getBrand());
					}
					ce.setCarBrand(brands);
				}else{
					ce.setCarBrand("");
				}
				ce.setValidCarQuantity(owner.getCarnum1()==null?"0":owner.getCarnum1()+"");
				ce.setHistoryCarQuantity(owner.getCarnum2()==null?"0":owner.getCarnum2()+"");
				ce.setOrderQuantity(owner.getSureordercount()==null?"0":owner.getSureordercount()+"");
				ce.setOrderGDP(owner.getGdp()==null?"0":owner.getGdp()+"");
				ce.setPlatformBrokerage(owner.getAvg()==null?"0":owner.getAvg()+"");
				ce.setProfit(owner.getChajialirun()==null?"0":owner.getChajialirun()+"");
				ce.setCanWithdrawMoney(owner.getKetixianjiner()==null?"0":owner.getKetixianjiner()+"");
				ce.setWithdrawingMoney(owner.getCashbalance()==null?"0":owner.getCashbalance()+"");
				ce.setWithdrawnMoney(owner.getAlreadybalance()==null?"0":owner.getAlreadybalance()+"");
			}else{
				ce.setCarBrand("");
				ce.setValidCarQuantity("0");
				ce.setHistoryCarQuantity("0");
				ce.setOrderQuantity("0");
				ce.setOrderGDP("0");
				ce.setPlatformBrokerage("0");
				ce.setProfit("0");
				ce.setCanWithdrawMoney("0");
				ce.setWithdrawingMoney("0");
				ce.setWithdrawnMoney("0");
			}
			ce.setRegisterTime(users.getCreateAt()==null?"":format.format(users.getCreateAt()));
			ce.setLastLoginTime(users.getUpdateAt()==null?"":format.format(users.getUpdateAt()));
			ce.setLoginQuantity(users.getLoginCount()==null?"":users.getLoginCount()+"");
			ce.setCreditScore(users.getCreditScore()==null?"":users.getCreditScore()+"");
			ce.setBalance(users.getBalance()==null?"0":users.getBalance()+"");
			
			exportList.add(ce);
			i++;
		}
		
		ExportCarOwnerController<CarOwnerExport> ex=new ExportCarOwnerController<CarOwnerExport>();
		String[] headers =  {"序号","ID","姓名","手机","性别","车辆品牌","有效车辆数","历史车辆数","注册时间","最后一次登陆时间","登录次数","接单次数","订单GDP","平台分佣","差价利润","信用分","账户余额","可提现金额","提现中金额","已提现金额"};
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
        exportExcel("车东信息列表", null, dataset, out, "yyyy-MM-dd");  
    }  
  
    public void exportExcel(String[] headers, Collection<T> dataset,  
            OutputStream out)  
    {  
        exportExcel("车东信息列表", headers, dataset, out, "yyyy-MM-dd");  
    }  
  
    public void exportExcel(String[] headers, Collection<T> dataset,  
            OutputStream out, String pattern)  
    {  
        exportExcel("车东信息列表", headers, dataset, out, pattern);  
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
