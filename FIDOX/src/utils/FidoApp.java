package utils;

import java.util.HashMap;
import java.util.Map;

public class FidoApp {
	private static FidoApp fidoApp = new FidoApp();
	private Map<String, String> sitesPropertiesMap = new HashMap<>();
	
	private FidoApp(){
	}
	
	public static FidoApp getFidoAppInstance() {
		return fidoApp;
	}

	public Map<String, String> getSitesPropertiesMap() {
		return sitesPropertiesMap;
	}

	public void setSitesPropertiesMap(Map<String, String> sitesPropertiesMap) {
		this.sitesPropertiesMap = sitesPropertiesMap;
	}
	
	
}
