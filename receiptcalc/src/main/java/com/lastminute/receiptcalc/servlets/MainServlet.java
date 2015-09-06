package com.lastminute.receiptcalc.servlets;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lastminute.receiptcalc.model.Receipt;
import com.lastminute.receiptcalc.services.ReceiptService;

@WebServlet(name = "mainServlet", urlPatterns = {"/main"})
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 2638127270022516617L;
	  
	  @Inject
	  private ReceiptService receiptService;
	  
	  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  doGet(request, response);
	  }
	  
	  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    if (request.getParameter("input") != null && request.getParameter("input").length() > 0) {
	    	PrintWriter out = response.getWriter();
	    	InputStream is = new ByteArrayInputStream(request.getParameter("input").getBytes(StandardCharsets.UTF_8));
	    	Receipt receipt = receiptService.buildReceiptFromInputStream(is);
	    	out.println("Receipt: \n" + receiptService.receiptToString(receipt));
		    out.close();
	    }
	  }	
}
