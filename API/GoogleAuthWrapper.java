package API;

import java.io.IOException;
import java.util.Map;

public class GoogleAuthWrapper {

	private IGoogleAuth googleAuth;

	public GoogleAuthWrapper(int listenPort, String clientId, String clientSecret) throws IOException {
		googleAuth = new GoogleAuthImpl(listenPort, clientId, clientSecret);
		googleAuth.startListening();
	}

	public Map<String, String> getUserInfo() throws IOException, OAuthException {
		String token = googleAuth.getToken();
		return googleAuth.getProfile(token);
	}
}
