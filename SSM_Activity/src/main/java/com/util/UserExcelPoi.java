package com.util;

import com.bean.Classes;
import com.bean.UserTb;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class UserExcelPoi {
    //创建一个Excel对象
    static HSSFWorkbook excel = new HSSFWorkbook();
    //创建一个sheet文件
    static HSSFSheet sheet = null;
    //创建保存文件头目录数组(指定首行数据)
    public static String[] heads = null;
    //创建第一行数据
    public static void createOne() {
        sheet = excel.createSheet();
        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < heads.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(heads[i]);
        }
    }

    //设置第二行及以后的数据
    public static void createOthers(List<UserTb> list) {
        for (int i = 0; i < list.size(); i++) {
            HSSFRow row = sheet.createRow(i + 1);
            UserTb userTb = list.get(i);
            //第一列
            HSSFCell cell1 = row.createCell(0);
            cell1.setCellValue(userTb.getUserId());
            //第二列
            HSSFCell cell2 = row.createCell(1);
            cell2.setCellValue(userTb.getUserName());
            //第三列
            HSSFCell cell3 = row.createCell(2);
            cell3.setCellValue(userTb.getUserRealname());
            //第四列
            HSSFCell cell4 = row.createCell(3);
            cell4.setCellValue(userTb.getRole().getRolename());
        }
    }

    //设置导出的操作
    public static void export(OutputStream outputStream) {
        //设置以网格的形式输出
        sheet.setGridsPrinted(true);
        try {
            excel.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
