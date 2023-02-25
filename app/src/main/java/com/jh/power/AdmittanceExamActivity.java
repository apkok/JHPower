package com.jh.power;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AdmittanceExamActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        readExcel("power_exam_distribution.xls", new String[]{"试题编号","试题正文","试题选项","试题答案"});
    }

    /**
     * 读取excel   （xls和xlsx）
     * @return
     */
    public List<AdmittanceExamModel> readExcel(String examText, String filePath, String columns[]) {
        Sheet sheet = null;
        Row row = null;
        Row rowHeader = null;
        List<AdmittanceExamModel> list = null;
        String cellData = null;
        Workbook wb = null;
        if (filePath == null) {
            return null;
        }
        String extString = filePath.substring(filePath.lastIndexOf("."));
        InputStream is = null;
        try {
            is = getResources().getAssets().open(filePath);
            if (".xls".equals(extString)) {
                wb = new HSSFWorkbook(is);
            } else if (".xlsx".equals(extString)) {
                wb = new XSSFWorkbook(is);
            } else {
                wb = null;
            }
            if (wb != null) {
                // 用来存放表中数据
                list = new ArrayList<>();
                // 获取第一个sheet
                sheet = wb.getSheetAt(0);
                // 获取最大行数
                int rownum = sheet.getPhysicalNumberOfRows();
                // 获取第一行
                rowHeader = sheet.getRow(0);
                row = sheet.getRow(0);
                // 获取最大列数
                int colnum = row.getPhysicalNumberOfCells();
                // 行
                for (int i = 1; i < rownum; i++) {
                    row = sheet.getRow(i);
                    AdmittanceExamModel model = new AdmittanceExamModel();
                    if (row != null) {
                        // 列
                        for (int j = 0; j < colnum; j++) {
                            if(columns[j].equals(getCellFormatValue(rowHeader.getCell(j)))){
                                cellData = (String) getCellFormatValue(row.getCell(j));

                                if(columns[0].equals(columns[j])) {
                                    model.setExamNo(cellData);
                                }else if(columns[1].equals(columns[j])) {
                                    model.setExamText(cellData);
                                }else if(columns[2].equals(columns[j])) {
                                    model.setExamOption(cellData);
                                }else if(columns[3].equals(columns[j])) {
                                    model.setExamAnswer(cellData);
                                }
                            }
                        }
                    } else {
                        break;
                    }
                    list.add(model);
                }

                //遍历list
                for (AdmittanceExamModel model : list) {

                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**	获取单个单元格数据
     * @param cell
     * @return
     * @author lizixiang ,2018-05-08
     */
    public Object getCellFormatValue(Cell cell) {
        Object cellValue = null;
        if (cell != null) {
            // 判断cell类型
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC: {
                    cellValue = String.valueOf(cell.getNumericCellValue());
                    break;
                }
                case Cell.CELL_TYPE_FORMULA: {
                    // 判断cell是否为日期格式
                    if (DateUtil.isCellDateFormatted(cell)) {
                        // 转换为日期格式YYYY-mm-dd
                        cellValue = cell.getDateCellValue();
                    } else {
                        // 数字
                        cellValue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                case Cell.CELL_TYPE_STRING: {
                    cellValue = cell.getRichStringCellValue().getString();
                    break;
                }
                default:
                    cellValue = "";
            }
        } else {
            cellValue = "";
        }
        return cellValue;
    }
}
