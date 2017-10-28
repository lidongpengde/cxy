package com.cxy.test;

import com.cxy.entity.User;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by lidongpeng on 2017/9/1.
 */
public class URLToDomain {
    /**
     * 根据url获取domain
     * @return
     * @throws IOException
     */

    public void parseDomain() throws IOException {
        User user = null;
        if (StringUtils.isEmpty(user.getMobile()))
            System.out.println("tinghaoder");

    }
    @Test
    public  void main() throws IOException {
        String sql="LineInfo";
        readExcelContent();

    }
    public Map<Integer, String> readExcelContent() throws IOException {
        InputStream stream = new FileInputStream("E:\\member_doc\\2016年\\集团统一登录项目\\日常记录\\留存记录\\购物车加黑冻结用户\\2017-10-28(14点)统计的僵尸用户.xlsx");
        String fileType = "xlsx";
        Map<Integer, String> content = new HashMap<Integer, String>();
        Workbook wb = null;
        if (fileType.equals("xls")) {
            wb = new HSSFWorkbook(stream);
        }
        else if(fileType.equals("xlsx"))
        {
            wb = new XSSFWorkbook(stream);
        }
        Sheet sheet1 = wb.getSheetAt(0);
        for (Row row : sheet1) {

            System.out.println(row.getCell(0));
        }
        return content;
    }
}
