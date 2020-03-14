package API;

import java.io.IOException;
import java.util.Map;

public interface IGoogleAuth {
	/**
	 * To be call first, this method start a local server to receive the code access
	 * from web browser
	 */
	void startListening();

	/**
	 * Get access token from Google OAuth 2 API
	 */
	String getToken() throws IOException, OAuthException;

	/**
	 * Get Google user info
	 * 
	 * @param token
	 *            The access token from getToken() method
	 * @return A map representing the JSON response from Google API
	 */
	Map<String, String> getProfile(String token) throws IOException, OAuthException;
}
