package API;

/**
 * This class controls Google API.
 *
 * @author Raiana Zaman Last Updated: 03/13/2020
 */

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;
import org.apache.commons.io.IOUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class GoogleAuthImpl implements IGoogleAuth {

	// Google endpoints
	private static final String AUTH_ENDPOINT = "https://accounts.google.com/o/oauth2/v2/auth?response_type=code&scope=profile&redirect_uri=%s&client_id=%s";
	private static final String TOKEN_ENDPOINT = "https://www.googleapis.com/oauth2/v4/token";
	private static final String USER_INFO_ENDPOINT = "https://www.googleapis.com/oauth2/v3/userinfo?access_token=";

	// Additional constants
	private static final String AUTH_REQUEST_BODY = "code=%s&redirect_uri=%s&client_id=%s&client_secret=%s&grant_type=authorization_code";
	private static final String CONTENT_TYPE = "application/x-www-form-urlencoded";
	private static final String ACCEPT_TYPE = "Accept=text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8";

	private static final TypeReference<HashMap<String, String>> TYPE_REFERENCE = new TypeReference<HashMap<String, String>>() {
	};
	
	//screen timeout
	private static final int TIMEOUT_MS = 180000;

	private String clientId;
	private String clientSecret;
	private BlockingQueue<String> code = new LinkedBlockingDeque<>(1);
	private ObjectMapper jsonMapper = new ObjectMapper();
	private String redirectUrl;
	private HttpServer server;

	public GoogleAuthImpl(int listenPort, String clientID, String clientSecret) throws IOException {
		this.clientId = clientID;
		this.clientSecret = clientSecret;

		// Create a server that listens to local host
		redirectUrl = "http://127.0.0.1:" + listenPort;
		server = HttpServer.create(new InetSocketAddress(listenPort), 0);
		HttpContext context = server.createContext("/");

		// Handler is call each time a request is received
		context.setHandler(httpExchange -> {
			try (OutputStream os = httpExchange.getResponseBody()) {
				// Read the request from browser, the request should contain a 'code' attribute
				List<NameValuePair> params = URLEncodedUtils.parse(httpExchange.getRequestURI(),
						StandardCharsets.UTF_8.name());
				for (NameValuePair param : params) {
					if ("code".equalsIgnoreCase(param.getName())) {
						// If there is an old code from the last call, remove is
						if (!code.isEmpty()) {
							code.poll(1, TimeUnit.SECONDS);
						}
						code.put(param.getValue());
					}
				}

				// Send response to browser
				String response = "<html><head><meta http-equiv='refresh' content='10;url=https://google.com'></head>"
						+ "<body>Please close this window and return to the app</body></html>";
				httpExchange.sendResponseHeaders(200, response.length());
				os.write(response.getBytes());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
	}

	private HttpURLConnection getHttpConnection(String url, String method) throws IOException {
		// Create a simple HTTP connection
		HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
		connection.setRequestMethod(method);
		connection.setRequestProperty("Content-Type", CONTENT_TYPE);
		connection.setRequestProperty("Accept", ACCEPT_TYPE);
		connection.setConnectTimeout(TIMEOUT_MS);
		connection.setReadTimeout(TIMEOUT_MS);
		connection.setDoOutput(true);
		return connection;
	}

	@Override
	public void startListening() {
		server.start();
	}

	@Override
	public String getToken() throws IOException, OAuthException {
		try {
			// Open a web browser for manual authentication
			Desktop.getDesktop().browse(new URI(String.format(AUTH_ENDPOINT, redirectUrl, this.clientId)));

			/* Wait for the web browser to return the access code If user takes too 
			 * long to enter his credentials, throw an InterruptedException
			 */
			String accessCode = Optional.ofNullable(code.poll(TIMEOUT_MS, TimeUnit.MILLISECONDS))
					.orElseThrow(() -> new OAuthException("Timeout while waiting for access code!"));
			System.out.println("Google's access code: " + accessCode);

			// Build another request to Google to get an access token
			HttpURLConnection con = getHttpConnection(TOKEN_ENDPOINT, "POST");
			try (OutputStream os = con.getOutputStream()) {
				// Build request body
				byte[] input = String.format(AUTH_REQUEST_BODY, accessCode, redirectUrl, this.clientId, clientSecret)
						.getBytes(StandardCharsets.UTF_8);
				os.write(input, 0, input.length);

				// Get Google's response
				try (InputStream is = con.getInputStream()) {
					if (con.getResponseCode() != 200) {
						throw new OAuthException("Fail to get token: Google returns code: " + con.getResponseCode());
					}
					byte[] resp = IOUtils.toByteArray(is);
					return Optional.ofNullable(jsonMapper.readValue(resp, TYPE_REFERENCE).get("access_token"))
							.orElseThrow(() -> new OAuthException("Fail to get token: Token is null"));
				}
			}
		} catch (URISyntaxException e) {
			throw new OAuthException("Invalid authentication endpoint URL syntax.", e);
		} catch (InterruptedException e) {
			throw new OAuthException("Unknown exception while getting access code.", e);
		}
	}

	@Override
	public Map<String, String> getProfile(String token) throws IOException, OAuthException {
		HttpURLConnection con = getHttpConnection(USER_INFO_ENDPOINT + token, "GET");
		try (InputStream is = con.getInputStream()) {
			if (con.getResponseCode() != 200) {
				throw new OAuthException("Fail to get user's profile: Google returns code: " + con.getResponseCode());
			}
			byte[] resp = IOUtils.toByteArray(is);
			return jsonMapper.readValue(resp, TYPE_REFERENCE);
		}
	}
}
