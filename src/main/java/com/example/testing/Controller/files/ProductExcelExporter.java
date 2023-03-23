package com.example.testing.Controller.files;

import com.example.testing.model.Product;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.*;

import java.io.IOException;
import java.util.List;

public class ProductExcelExporter extends AbstractExporter{


    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    public ProductExcelExporter(){
        workbook=new XSSFWorkbook();
    }
    private void writeHeaderLine(){
        sheet = workbook.createSheet("users");
        XSSFRow row = sheet.createRow(0);

        XSSFCellStyle cellStyle= workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        cellStyle.setFont(font);


        createCell(row,0,"Product Name",cellStyle);
        createCell(row,1,"Product Brand",cellStyle);
        createCell(row,2,"Product Description",cellStyle);
        createCell(row,3,"Product Cost",cellStyle);
        createCell(row,4,"Product Category",cellStyle);

    }

    private void  createCell(XSSFRow row, int columnIndex, Object value, CellStyle style){
        XSSFCell cell= row.createCell(columnIndex);
        sheet.autoSizeColumn(columnIndex);

        if(value instanceof Integer){
            cell.setCellValue((Integer) value);
        }else if(value instanceof Boolean){
            cell.setCellValue((Boolean) value);
        } else{
            cell.setCellValue((String) value);
        }

        cell.setCellStyle(style);

    }
    public void export(List<Product> listProduct, HttpServletResponse response) throws IOException {

        super.setResponseHeader(response,"application/octet-stream",".xlsx");

        writeHeaderLine();
        writeDataLines(listProduct);

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();

    }
    private void writeDataLines(List<Product> listProduct){

        int rowIndex = 1;

        XSSFCellStyle cellStyle= workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        cellStyle.setFont(font);

        for (Product product : listProduct){
            XSSFRow row =sheet.createRow(rowIndex ++);
            int columnIndex =0;
            createCell(row,columnIndex ++,product.getProductName(),cellStyle);
            createCell(row,columnIndex ++,product.getBrand(),cellStyle);
            createCell(row,columnIndex ++,product.getDescription(),cellStyle);
            createCell(row,columnIndex ++,product.getCost(),cellStyle);
            createCell(row,columnIndex ++,product.getCategory().getCategoryName(),cellStyle);

        }
    }

}
