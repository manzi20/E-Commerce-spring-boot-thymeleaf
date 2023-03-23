package com.example.testing.Controller.files;

import com.example.testing.model.Product;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class ProductPdfExporter extends AbstractExporter {
    public void export(List<Product> listProduct, HttpServletResponse response) throws IOException {
        super.setResponseHeader(response,"application/pdf",".pdf");

        Document document= new Document(PageSize.A4);
        PdfWriter.getInstance(document,response.getOutputStream());

        document.open();

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph paragraph= new Paragraph("List of products",font);
        paragraph.setAlignment((Paragraph.ALIGN_CENTER));

        document.add(paragraph);

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100f);
        table.setSpacingBefore(10);
        table.setWidths(new float[]{1.5f,2.5f,5.0f,2.0f});


        writeTableHeader(table);
        writeTableData(table,listProduct);

        document.add(table);

        document.close();

    }
    public void writeTableHeader(PdfPTable table){
        PdfPCell cell=new PdfPCell();
        cell.setBackgroundColor(Color.blue);
        cell.setPadding(5);

        Font font =FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Product Name",font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Product Brand ",font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Product Description",font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Product Cost",font));
        table.addCell(cell);

//        cell.setPhrase(new Phrase("Role",font));
//        table.addCell(cell);

    }

    private void writeTableData(PdfPTable table, List<Product> listProduct){
        for(Product product : listProduct){
            table.addCell(product.getProductName());
            table.addCell(product.getBrand());
            table.addCell(product.getDescription());
            table.addCell(product.getCost());
        }
    }

}
