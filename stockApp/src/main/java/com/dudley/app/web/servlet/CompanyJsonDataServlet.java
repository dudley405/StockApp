package com.dudley.app.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.dudley.app.entities.PriceHistory;
import com.dudley.app.service.CompanyService;
import com.dudley.app.service.PriceHistoryService;
import com.google.gson.Gson;

@WebServlet("/CompanyJsonDataServlet")
public class CompanyJsonDataServlet extends HttpServlet {
	
	@Autowired
	PriceHistoryService historyService;
	
	@Autowired
	CompanyService companyService;
	
	private static final long serilVersionUID = 1L;
	
	public CompanyJsonDataServlet() {
		super();
	}
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
			      config.getServletContext());
	}
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		Set<PriceHistory> priceHistories = companyService.findByTicker("ACT").getPriceHistories();
		
		List<PriceHistory> priceHistoriesList = new ArrayList<PriceHistory>();
		priceHistoriesList.addAll(priceHistories);
		
		Gson gson = new Gson();
		
		String jsonString = gson.toJson(priceHistoriesList);
		
		response.setContentType("application/json");
		
		response.getWriter().write(jsonString);
	}
	
}
