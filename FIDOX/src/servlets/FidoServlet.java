package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import models.CurrencyObj;
import utils.HttpUtils;

//currency exchange servlet - takes date, currency to exchange from and a list of currencies to exchange to.
//example:
//http://localhost:8080/FIDO/exchange?date=2012-01-03&from=USD&to=ILS,CAD
	
@WebServlet("/exchange")
public class FidoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(FidoServlet.class);

	Gson json = new GsonBuilder().create();

	public FidoServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		String site = request.getParameter("site");
		String[] requestParams = {request.getParameter("date"), request.getParameter("from"), request.getParameter("to")};

		logger.info("Get request from host: " + request.getRemoteHost() + " " + request.getRequestURI()
				+ " for site: " + site + ". Parameter: date =  " + requestParams[0] + " base: " + requestParams[1] + " symbols: " + requestParams[2]);

		String httpCurrencyRequestResult = null;
		try {
			httpCurrencyRequestResult = HttpUtils.doHttpCurrencyRequest(site, requestParams);
			logger.info("results: " + httpCurrencyRequestResult);

			//convert json returned from site to CurrencyObj
			CurrencyObj c = json.fromJson(httpCurrencyRequestResult, CurrencyObj.class);
			response.getWriter().append(c.toString());
		} catch (IllegalArgumentException | IOException e) {
			logger.info("Found error: " + e.getLocalizedMessage());
			try {
				response.getWriter().append("Invalid paramters please check again.");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		logger.info("Request complete");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
