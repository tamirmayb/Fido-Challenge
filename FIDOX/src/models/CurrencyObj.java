package models;

import java.io.Serializable;
import java.util.HashMap;

public class CurrencyObj implements Serializable{
	
	private static final long serialVersionUID = 1L;
	String base;
	String date;
	
	HashMap<String,String> rates;

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	

	public HashMap<String, String> getRates() {
		return rates;
	}

	public void setRates(HashMap<String, String> rates) {
		this.rates = rates;
	}

	@Override
	public String toString() {
		return "CurrencyObj [base=" + base + ", date=" + date + ", rates=" + rates + "]";
	}

}
