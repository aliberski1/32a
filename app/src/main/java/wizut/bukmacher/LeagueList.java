package wizut.bukmacher;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Class providing list of leagues available through selected API. Exposes only
 * {@link #getLeagues()}.
 * 
 * @author Przemysław Arlet, Patryk Trzepizur
 * @version 0.1
 * @see {@link #getLeagues()}
 */
public class LeagueList {
	final static String APIURL = "http://api.football-data.org/v1/competitions";
	final static String APIKEY = "c2ba64b46cae4d4b929dadc73bc703c0";
	
	/**
	 * Converts InputStream to String.
	 * 
	 * Internally, Scanner iterates over tokens in the stream, which we separate
	 * using "beginning of the input boundary" control character (\A) thus
	 * giving only one token for the entire contents of the stream. That way
	 * we can access it using single {@code .next()}.
	 * 
	 * @param in — InputStream to convert to String.
	 * @return     String representation of the only parameter.
	 * @see        {@link #makeHTTPRequest(URL)}
	 */
	private static String streamToString(InputStream in) {
		Scanner s = null;
		try {
			s = new Scanner(in, "UTF-8").useDelimiter("\\A");
			return s.hasNext() ? s.next() : "";
		}
		finally {
			s.close();
		}
	}
	
	/**
	 * Launches HTTP request.
	 * 
	 * @param url — The URL to query.
	 * @return      String representation of response or null on internal
	 *              IOException.
	 */
	private static String makeHTTPRequest(URL url) {
		HttpURLConnection urlConnection = null;
		try {
			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestProperty("X-Auth-Token", APIKEY);
			final InputStream in = new BufferedInputStream(urlConnection.getInputStream());
			return streamToString(in);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			urlConnection.disconnect();
		}
		return null; // If .openConnection() or .getInputStream() thrown IOException
	}
	
	/**
	 * Gets codes of all leagues from football-data.org API.
	 * 
	 * @param json — JSON response from aforementioned API.
	 * @return       String[] with codes of all leagues.
	 */
	private static String[] getLeagueCodes(String json) throws JSONException {
		final JSONArray objectArray = new JSONArray(json);
		final String[] leagueCodes = new String[objectArray.length()];
		
		for (byte i = 0; i < objectArray.length(); i++) {
			leagueCodes[i] = objectArray.getJSONObject(i).getString("league");
		}
		
		return leagueCodes;
	}
	
	/**
	 * Get names and countries of leagues that are available through API we use.
	 * 
	 * @return Array of Strings with names of leagues.
	 */
	public static String[] getLeagues() {
		// HashMap to translate league codes to formatted String
		final Map<String, String> hmap = new HashMap<>();
		hmap.put("BL1", "1. Bundesliga (Germany)");
		hmap.put("BL2", "2. Bundesliga (Germany)");
		hmap.put("BL3", "3. Bundesliga (Germany)");
		hmap.put("DFB", "Dfb-Cup (Germany)");
		hmap.put("PL", "Premiere League (England)");
		hmap.put("EL1", "League One (England)");
		hmap.put("ELC", "Championship (England)");
		hmap.put("FAC", "FA-Cup (England)");
		hmap.put("SA", "Serie A (Italy)");
		hmap.put("SB", "Serie B (Italy)");
		hmap.put("PD", "Primera Division (Spain)");
		hmap.put("SD", "Segunda Division (Spain)");
		hmap.put("CDR", "Copa del Rey (Spain)");
		hmap.put("FL1", "Ligue 1 (France)");
		hmap.put("FL2", "Ligue 2 (France)");
		hmap.put("DED", "Eredivisie (Netherlands)");
		hmap.put("PPL", "Primeira Liga (Portugal)");
		hmap.put("GSL", "Super League (Greece)");
		hmap.put("CL", "Champions-League (Europe)");
		hmap.put("EL", "UEFA-Cup (Europe)");
		hmap.put("EC", "European-Cup of Nations (Europe)");
		hmap.put("WC", "World-Cup (World)");
		
		try {
			final String[] leagueCodes = getLeagueCodes(makeHTTPRequest(new URL(APIURL)));
			final String[] leagues = new String[leagueCodes.length];
			
			for (byte i = 0; i < leagueCodes.length; i++) {
				final String leagueName = hmap.get(leagueCodes[i]);
				
				// If unknown league available, use it code instead of name
				leagues[i] = leagueName == null ? leagueCodes[i] : leagueName;
			}
			
			return leagues;
		}
		catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null; // If URL constructor thrown MalformedURLException
	}
}
