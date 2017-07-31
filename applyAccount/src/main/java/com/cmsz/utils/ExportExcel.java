package com.cmsz.utils;

<<<<<<< HEAD
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

=======
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
>>>>>>> myDevelop
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
<<<<<<< HEAD

=======
>>>>>>> myDevelop
import com.cmsz.bean.ApplyMsg;
import com.cmsz.bean.RemarkBean;
import com.cmsz.service.IApplyMsgService;
import com.cmsz.service.impl.ApplyMsgService;

public class ExportExcel {

<<<<<<< HEAD
	public static void exportExcelTo(String path,ApplyMsg apply){
=======
	public static void exportExcel(ApplyMsg apply,HttpServletResponse response){
>>>>>>> myDevelop
		
		// 第一步，创建一个webbook（工作簿），对应一个Excel文件  
        @SuppressWarnings("resource")
		HSSFWorkbook wb = new HSSFWorkbook();  
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet("账号开户申请表");  
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
        HSSFRow row1 = sheet.createRow(0);  
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        // 创建一个水平居中格式
        HSSFCellStyle style1 = wb.createCellStyle();
        HSSFFont font1 = wb.createFont();
        font1.setFontName("黑体");
        font1.setFontHeightInPoints((short)20);
        font1.setBold(true);
        style1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style1.setFont(font1);
        // 创建一个垂直/水平居中格式
        HSSFCellStyle style2 = wb.createCellStyle();
        HSSFFont font2 = wb.createFont();
        font2.setFontHeightInPoints((short)14);
        style2.setFont(font2);
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 创建一个自动换行格式 
        HSSFCellStyle style3 = wb.createCellStyle();
        style3.setFont(font2);
        style3.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
        style3.setWrapText(true); 
        // 设置字体  
        HSSFCellStyle style4 = wb.createCellStyle(); 
        //字体加粗  
        HSSFFont font3 = wb.createFont();
        font3.setFontName("黑体");
        font3.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font3.setFontHeightInPoints((short)14);
        style4.setFont(font3);
        
        HSSFCellStyle style5 = wb.createCellStyle(); 
        font2.setFontHeightInPoints((short)14);
        style5.setFont(font2);
        
        sheet.setDefaultColumnStyle(0, style5);
        sheet.setDefaultColumnStyle(1, style5);
        sheet.setDefaultColumnStyle(2, style5);
        sheet.setDefaultColumnStyle(3, style5);
        sheet.setDefaultColumnStyle(4, style5);
        sheet.setDefaultColumnStyle(5, style5);
<<<<<<< HEAD
//        sheet.autoSizeColumn(0);
//        sheet.autoSizeColumn(1);
//        sheet.autoSizeColumn(2);
//        sheet.autoSizeColumn(3);
//        sheet.autoSizeColumn(4);
//        sheet.autoSizeColumn(5);
=======
>>>>>>> myDevelop

        //合并单元格
        CellRangeAddress region1 = new CellRangeAddress(0, 0, 0, 5);
		sheet.addMergedRegion(region1);
        CellRangeAddress region2 = new CellRangeAddress(1, 8, 0, 0);
		sheet.addMergedRegion(region2);
		CellRangeAddress region3 = new CellRangeAddress(9, 14, 0, 0);
		sheet.addMergedRegion(region3);
		CellRangeAddress region4 = new CellRangeAddress(8, 8, 1, 5);
		sheet.addMergedRegion(region4);
		CellRangeAddress region5 = new CellRangeAddress(14, 14, 1, 5);
		sheet.addMergedRegion(region5);
		CellRangeAddress region6 = new CellRangeAddress(1, 1, 2, 5);
		sheet.addMergedRegion(region6);
		CellRangeAddress region7 = new CellRangeAddress(2, 2, 4, 5);
		sheet.addMergedRegion(region7);
		CellRangeAddress region8 = new CellRangeAddress(4, 4, 3, 5);
		sheet.addMergedRegion(region8);
		CellRangeAddress region9 = new CellRangeAddress(5, 5, 2, 5);
		sheet.addMergedRegion(region9);
		CellRangeAddress region10 = new CellRangeAddress(6, 6, 2, 5);
		sheet.addMergedRegion(region10);
		CellRangeAddress region11 = new CellRangeAddress(11, 11, 2, 5);
		sheet.addMergedRegion(region11);
		CellRangeAddress region12 = new CellRangeAddress(12, 12, 2, 5);
		sheet.addMergedRegion(region12);
		CellRangeAddress region13 = new CellRangeAddress(13, 13, 1, 5);
		sheet.addMergedRegion(region13);
		CellRangeAddress region14 = new CellRangeAddress(18, 18, 0, 5);
		sheet.addMergedRegion(region14);
		CellRangeAddress region15 = new CellRangeAddress(9, 9, 4, 5);
		sheet.addMergedRegion(region15);
		CellRangeAddress region16 = new CellRangeAddress(10, 10, 4, 5);
		sheet.addMergedRegion(region16);
		CellRangeAddress region17 = new CellRangeAddress(7, 7, 1, 5);
		sheet.addMergedRegion(region17);
		//设置列宽
		sheet.setColumnWidth(0, 7300);
		sheet.setColumnWidth(1, 9500);
		sheet.setColumnWidth(2, 5000);
		sheet.setColumnWidth(3, 5000);
		sheet.setColumnWidth(4, 5000);
		sheet.setColumnWidth(5, 5700);
		
		//创建表格
		HSSFCell cell_1_1 = row1.createCell(0);  
        cell_1_1.setCellValue("账号开户申请表");  
        cell_1_1.setCellStyle(style1);
        
        HSSFRow row2 = sheet.createRow(1);
        HSSFCell cell_2_1 = row2.createCell(0);
        cell_2_1.setCellValue("帐\n号\n信\n息");
        cell_2_1.setCellStyle(style2);
        HSSFCell cell_2_2 = row2.createCell(1);
        cell_2_2.setCellValue("帐户名");
        HSSFCell cell_2_3 = row2.createCell(2);
        cell_2_3.setCellValue(apply.getAccountName());
        
        HSSFRow row3 = sheet.createRow(2);
        HSSFCell cell_3_2 = row3.createCell(1);
        cell_3_2.setCellValue("所属组");
        HSSFCell cell_3_3 = row3.createCell(2);
        cell_3_3.setCellValue(apply.getGroupName());
        HSSFCell cell_3_4 = row3.createCell(3);
        cell_3_4.setCellValue("主路径");
        HSSFCell cell_3_5 = row3.createCell(4);
        cell_3_5.setCellValue(apply.getMainPath());
        
        HSSFRow row4 = sheet.createRow(3);
        Integer accountPropertices = apply.getAccountProperties();
        HSSFCell cell_4_2 = row4.createCell(1);
        cell_4_2.setCellValue("账号属性");
        HSSFCell cell_4_3 = row4.createCell(2);
        cell_4_3.setCellValue(Constant.ACCOUNT_PROPERTICES_STR1);
        HSSFCell cell_4_4 = row4.createCell(3);
        cell_4_4.setCellValue(Constant.ACCOUNT_PROPERTICES_STR2);
        HSSFCell cell_4_5 = row4.createCell(4);
        cell_4_5.setCellValue(Constant.ACCOUNT_PROPERTICES_STR3);
        HSSFCell cell_4_6 = row4.createCell(5);
        cell_4_6.setCellValue(Constant.ACCOUNT_PROPERTICES_STR4);
        if(accountPropertices.equals(1)){
        	cell_4_3.setCellStyle(style4);
        }else if(accountPropertices.equals(2)){
        	cell_4_4.setCellStyle(style4);
        }else if(accountPropertices.equals(3)){
        	cell_4_5.setCellStyle(style4);
        }else{
        	cell_4_6.setCellStyle(style4);
        }
        
        HSSFRow row5 = sheet.createRow(4);
        HSSFCell cell_5_2 = row5.createCell(1);
        cell_5_2.setCellValue("账号使用期限");
        HSSFCell cell_5_3 = row5.createCell(2);
        cell_5_3.setCellValue("长期");
        HSSFCell cell_5_4 = row5.createCell(3);
        SimpleDateFormat fm = new SimpleDateFormat("yyyy年MM月dd日");
        String startTime = fm.format(apply.getStartTime());
        String endTime = fm.format(apply.getEndTime());
        cell_5_4.setCellValue(startTime+"至"+endTime);
        
        HSSFRow row6 = sheet.createRow(5);
        HSSFCell cell_6_2 = row6.createCell(1);
        cell_6_2.setCellValue("账号所在的硬件设备/软件平台");
        HSSFCell cell_6_3 = row6.createCell(2);
        cell_6_3.setCellValue(apply.getPlatform());
        
        HSSFRow row7 = sheet.createRow(6);
        HSSFCell cell_7_2 = row7.createCell(1);
        cell_7_2.setCellValue("所属系统");
        HSSFCell cell_7_3 = row7.createCell(2);
        cell_7_3.setCellValue(apply.getSubSystem());
        
        HSSFRow row8 = sheet.createRow(7);
        HSSFCell cell_8_2 = row8.createCell(1);
        cell_8_2.setCellValue("可访问那些资源和权限（读/写/可执行）：");
        
        HSSFRow row9 = sheet.createRow(8);
        row9.setHeight((short)1200);
        HSSFCell cell_9_2 = row9.createCell(1);
        cell_9_2.setCellValue("\t"+apply.getPowerDetail());
        cell_9_2.setCellStyle(style3);
        
        HSSFRow row10 = sheet.createRow(9);
        HSSFCell cell_10_1 = row10.createCell(0);
        cell_10_1.setCellValue("账号使用人员信息");
        cell_10_1.setCellStyle(style1);
        cell_10_1.setCellStyle(style2);
        HSSFCell cell_10_2 = row10.createCell(1);
        cell_10_2.setCellValue("姓名");
        HSSFCell cell_10_3 = row10.createCell(2);
        cell_10_3.setCellValue(apply.getRealName());
        HSSFCell cell_10_4 = row10.createCell(3);
        cell_10_4.setCellValue("手机号码");
        HSSFCell cell_10_5 = row10.createCell(4);
        cell_10_5.setCellValue(apply.getPhoneNumber());
        
        HSSFRow row11 = sheet.createRow(10);
        HSSFCell cell_11_2 = row11.createCell(1);
        cell_11_2.setCellValue("工作单位");
        HSSFCell cell_11_3 = row11.createCell(2);
        cell_11_3.setCellValue(apply.getWorkCom());
        HSSFCell cell_11_4 = row11.createCell(3);
        cell_11_4.setCellValue("工作职责");
        HSSFCell cell_11_5 = row11.createCell(4);
        cell_11_5.setCellValue(apply.getWorkDuty());
        
        HSSFRow row12 = sheet.createRow(11);
        HSSFCell cell_12_2 = row12.createCell(1);
        cell_12_2.setCellValue("4A主账号");
        HSSFCell cell_12_3 = row12.createCell(2);
        cell_12_3.setCellValue(apply.getFourA());
        
        HSSFRow row13 = sheet.createRow(12);
        HSSFCell cell_13_2 = row13.createCell(1);
        cell_13_2.setCellValue("电子邮箱");
        HSSFCell cell_13_3 = row13.createCell(2);
        cell_13_3.setCellValue(apply.getEmail());
        
        HSSFRow row14 = sheet.createRow(13);
        HSSFCell cell_14_2 = row14.createCell(1);
        cell_14_2.setCellValue("申请原因");
        
        HSSFRow row15 = sheet.createRow(14);
        row15.setHeight((short)1200);
        HSSFCell cell_15_2 = row15.createCell(1);
        cell_15_2.setCellValue("\t"+apply.getApplyReason());
        cell_15_2.setCellStyle(style3);
        
        Map<String, RemarkBean> map = new HashMap<String, RemarkBean>();
        String remark=apply.getRemark();
        if(remark!=null){
        	String[] oneRemark = apply.getRemark().split(";");
            for (int i = 0; i < oneRemark.length; i++) {
            	String[] content = oneRemark[i].split(":");
    			RemarkBean remarkBean = new RemarkBean(content[0], content[1], content[2]);
    			map.put("handlePerson"+(i+1), remarkBean);
            }
        }
        
        HSSFRow row16 = sheet.createRow(15);
        HSSFCell cell_16_1 = row16.createCell(0);
        cell_16_1.setCellValue("业务使用部门主管意见");
        HSSFCell cell_16_2 = row16.createCell(1);
        HSSFCell cell_16_3 = row16.createCell(2);
        cell_16_3.setCellValue("签名");
        HSSFCell cell_16_4 = row16.createCell(3);
        HSSFCell cell_16_5 = row16.createCell(4);
        cell_16_5.setCellValue("日期");
        HSSFCell cell_16_6 = row16.createCell(5);
        if(map.get("handlePerson1") != null){
        	cell_16_2.setCellValue(map.get("handlePerson1").getRemark());
            cell_16_4.setCellValue(map.get("handlePerson1").getRealName());
            cell_16_6.setCellValue(map.get("handlePerson1").getDate());
        }
        
        HSSFRow row17 = sheet.createRow(16);
        HSSFCell cell_17_1 = row17.createCell(0);
<<<<<<< HEAD
        cell_17_1.setCellValue("业务使用部门主管意见");
=======
        cell_17_1.setCellValue("部门主管意见");
>>>>>>> myDevelop
        HSSFCell cell_17_2 = row17.createCell(1);
        HSSFCell cell_17_3 = row17.createCell(2);
        cell_17_3.setCellValue("签名");
        HSSFCell cell_17_4 = row17.createCell(3);
        HSSFCell cell_17_5 = row17.createCell(4);
        cell_17_5.setCellValue("日期");
        HSSFCell cell_17_6 = row17.createCell(5);
        if(map.get("handlePerson2") != null){
        	cell_17_2.setCellValue(map.get("handlePerson2").getRemark());
        	cell_17_4.setCellValue(map.get("handlePerson2").getRealName());
        	cell_17_6.setCellValue(map.get("handlePerson2").getDate());
        }
        
        HSSFRow row18 = sheet.createRow(17);
        HSSFCell cell_18_1 = row18.createCell(0);
<<<<<<< HEAD
        cell_18_1.setCellValue("业务使用部门主管意见");
=======
        cell_18_1.setCellValue("配置管理员意见");
>>>>>>> myDevelop
        HSSFCell cell_18_2 = row18.createCell(1);
        HSSFCell cell_18_3 = row18.createCell(2);
        cell_18_3.setCellValue("签名");
        HSSFCell cell_18_4 = row18.createCell(3);
        HSSFCell cell_18_5 = row18.createCell(4);
        cell_18_5.setCellValue("日期");
        HSSFCell cell_18_6 = row18.createCell(5);
        if(map.get("handlePerson3") != null){
        	cell_18_2.setCellValue(map.get("handlePerson3").getRemark());
        	cell_18_4.setCellValue(map.get("handlePerson3").getRealName());
        	cell_18_6.setCellValue(map.get("handlePerson3").getDate());
        }
        
        HSSFRow row19 = sheet.createRow(18);
        row19.setHeight((short)2500);
        HSSFCell cell_19_1 = row19.createCell(0);
        cell_19_1.setCellStyle(style3);  
        cell_19_1.setCellValue(Constant.SHUOMING);
        
        // 第五步，将文件存到指定位置  
<<<<<<< HEAD
        try  
        {  
            FileOutputStream fout = new FileOutputStream(path+"/students2.xls");  
            wb.write(fout);  
            fout.close();  
=======
        OutputStream out = null;
        try{  
        	out = response.getOutputStream();
        	SimpleDateFormat fms = new SimpleDateFormat("yyyyMMdd");
        	Date date = new Date();
            String fileName = apply.getUsername()+"账户开户申请表"+fms.format(date)+".xls";// 文件名  
            response.setContentType("application/x-msdownload");  
            response.setHeader("Content-Disposition", "attachment; filename="  
                                                    + URLEncoder.encode(fileName, "UTF-8"));  
            wb.write(out);  
>>>>>>> myDevelop
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }  
    }
	
	public static void main(String[] args) {
<<<<<<< HEAD
		IApplyMsgService applyService = new ApplyMsgService();
		ApplyMsg apply = applyService.look(83);
		exportExcelTo("D:", apply);
		System.out.println("!!!!!!!!");
=======
		System.out.println("!!!!!!!!");
	}

	public void test(int id,HttpServletResponse response){
		IApplyMsgService applyService = new ApplyMsgService();
		ApplyMsg apply = applyService.look(83);
		exportExcel(apply,response);
>>>>>>> myDevelop
	}
}
