package com.dudley.app.load;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dudley.app.entities.Company;
import com.dudley.app.service.CompanyService;

@Service
public class CompanyLoader {

	@Autowired
	CompanyService companyService;

	public void run() {

		Reader in = null;
		
		try {
			in = new FileReader("C:\\Users\\lenovo\\git\\StockApp\\stockApp\\src\\main\\java\\com\\dudley\\app\\load\\WIKI_tickers.csv");
			Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader().parse(in);
			for (CSVRecord record : records) {
				
				System.out.println(record.get("quandl_code") + " ****************************");

				String ticker = record.get("quandl_code");
				String companyName = record.get("name");

				Company company = new Company();
				company.setStockTicker(ticker);
				company.setCompanyName(companyName);
				
				System.out.println(company.getStockTicker());
				System.out.println(company.getCompanyName());

				companyService.saveCompany(company);
				
				companyService.commit();
				companyService.clear();

			}
		} catch (Exception e) {
			 e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
