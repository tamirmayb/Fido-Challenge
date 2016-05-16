package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

import org.apache.log4j.Logger;

public class HttpUtils {
	final static Logger logger = Logger.getLogger(HttpUtils.class);

	public static String doHttpCurrencyRequest(String site, String[] params)
			throws IOException, IllegalArgumentException {
		BufferedReader in;
		StringBuilder sb = new StringBuilder();
		HttpURLConnection connection = null;

		logger.info("Checking currency exchange rates on site: " + site);
		logger.info("params = " + Arrays.toString(params));

		String urlString = FidoApp.getFidoAppInstance().getSitesPropertiesMap().get(site);
		if (urlString != null && !urlString.isEmpty()) {
			urlString = String.format(urlString, params[0], params[1], params[2]);
			URL url = new URL(urlString);

			logger.info("Connecting to site...");
			connection = (HttpURLConnection) url.openConnection();

			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				sb.append(inputLine);
			}
			logger.info("Got results from site: " + sb.toString());
			return sb.toString();
		} else {
			logger.info("Error - Site requested has not yet been defined");
			throw new IllegalArgumentException("Site requested has not yet been defined, please contact administrator");
		}
	}

}
