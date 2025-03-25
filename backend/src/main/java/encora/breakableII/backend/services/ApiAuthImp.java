package encora.breakableII.backend.services;

import encora.breakableII.backend.models.TokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class ApiAuthImp implements ApiAuth{
    @Value("${amadeus.api.key}")
    private String apiKey;

    @Value("{amadeus.api.secret}")
    private String apiSecret;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public String getAccessToken() {
        String url = "https://test.api.amadeus.com/v1/security/oauth2/token";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "client_credentials");
        body.add("client_id", apiKey);
        body.add("client_secret", apiSecret);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        var response = restTemplate.postForEntity(url, request, TokenResponse.class);
        System.out.println(response.getBody());
        return response.getBody().getAccess_token();
    }
}
