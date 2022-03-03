package com.ibm.academia.restapi.fraudes.datos;

import java.math.BigDecimal;

import com.ibm.academia.restapi.fraudes.modelo.entidades.Fraude;

public class DatosDummy {

	public static Fraude fraude01() {
		return new Fraude(null, "123.123.123.123", "China", "CXN", "CHN", new BigDecimal(7.20), "Clog10");
	}
	
	public static Fraude fraude02() {
		return new Fraude(null,"189.125.127.186", "Mexico", "MEX", "MXN", new BigDecimal(22.24), "Clog10");
	}
	
	public static Fraude fraude03() {
		return new Fraude(null, "5.5.5.5", "Germany", "GER", "EUR", new BigDecimal(1), "Clog10");
	}
	
}
