package com.example.testing.Controller.files;

import com.example.testing.model.Product;
import jakarta.servlet.http.HttpServletResponse;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.IOException;
import java.util.List;

public class ProductCsvExporter extends AbstractExporter {

    public void export(List<Product> listProduct, HttpServletResponse response) throws IOException {
        super.setResponseHeader(response,"text/csv",".csv");


        ICsvBeanWriter csvWritter = new CsvBeanWriter(response.getWriter(),
                CsvPreference.STANDARD_PREFERENCE);

        String[] csvHeader ={"Product_Name","Product_Brand","Description","Product_Cost",};
        String[] fieldMapping={"ProductName","Brand","Description","Cost",};

        csvWritter.writeHeader(csvHeader);

        for(Product product : listProduct){
            csvWritter.write(product,fieldMapping);
        }

        csvWritter.close();

    }

}