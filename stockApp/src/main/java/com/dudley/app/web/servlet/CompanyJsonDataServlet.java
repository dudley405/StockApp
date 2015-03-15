package com.dudley.app.web.servlet;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import com.dudley.app.dao.JsonDao;
import com.dudley.app.entities.Company;
import com.dudley.app.entities.PriceHistory;
import com.dudley.app.service.CompanyService;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

@Component("jsonServlet")
public class CompanyJsonDataServlet implements HttpRequestHandler {
	
	@Autowired
	JsonDao jsonDao;
	
	@Autowired
	CompanyService companyService;
	
	private static final long serilVersionUID = 1L;
	
	public CompanyJsonDataServlet() {
		super();
	}

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Company company = companyService.findByTicker("ACT");
		
		List<PriceHistory> priceHistoriesList = jsonDao.getPriceHistories(company.getCompanyId());
		
		Gson gson=new GsonBuilder().serializeNulls().registerTypeAdapter(DateTime.class,new JodaDateTimeAdapter()).registerTypeAdapter(LocalDate.class,new JodaLocalDateAdapter()).setPrettyPrinting().create();
		
		String jsonString = gson.toJson(priceHistoriesList);
		
		response.setContentType("application/json");
		
		response.getWriter().write(jsonString);
		
	}
	
	public class JodaDateTimeAdapter implements JsonSerializer<DateTime> { 
		 
		@Override
	    public JsonElement serialize(DateTime src, Type typeOfSrc, 
	        JsonSerializationContext context) { 
	      DateTimeFormatter fmt = ISODateTimeFormat.dateTimeNoMillis(); 
	      return new JsonPrimitive(fmt.print(src)); 
		}
	     
	  }  
	   
	  public class JodaLocalDateAdapter implements JsonSerializer<LocalDate> { 
	 
	    public JsonElement serialize(LocalDate src, Type typeOfSrc, 
	        JsonSerializationContext context) { 
	      DateTimeFormatter fmt = ISODateTimeFormat.date(); 
	      return new JsonPrimitive(fmt.print(src)); 
	    } 
	  } 
	
}
