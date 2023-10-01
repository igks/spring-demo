// package com.example.demo.services;

// import java.nio.charset.StandardCharsets;
// import java.util.Arrays;
// import java.util.Base64;

// import org.apache.oltu.oauth2.client.OAuthClient;
// import org.apache.oltu.oauth2.client.URLConnectionClient;
// import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
// import org.apache.oltu.oauth2.client.response.OAuthJSONAccessTokenResponse;
// import org.apache.oltu.oauth2.common.OAuth;
// import org.apache.oltu.oauth2.common.message.types.GrantType;
// import org.springframework.http.HttpEntity;
// import org.springframework.http.HttpHeaders;
// import org.springframework.http.MediaType;
// import org.springframework.http.ResponseEntity;
// import org.springframework.scheduling.annotation.Async;
// import org.springframework.stereotype.Service;
// import org.springframework.web.client.RestTemplate;

// import com.example.demo.constants.TargetUrl;
// import com.example.demo.iservices.IDFServices;
// import com.example.demo.models.Intent;
// import org.springframework.http.HttpMethod;

// @Service
// public class DFServices implements IDFServices {

// @Override
// @Async
// public String create(Intent intent) {
// try {
// String clientId =
// "150414610327-eqhkekkqe7qd82h0oig20bilaa298teo.apps.googleusercontent.com";
// String clientSecret = "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";
// String tokenUrl = "https://accounts.google.com/o/oauth2/v2/auth";

// // String token;
// // String bearerToken;

// // String credentials = clientId + ":" + clientSecret;
// // String encodedCredentials = new
// // String(Base64.encodeBase64(credentials.getBytes()));

// // HttpHeaders headers = new HttpHeaders();
// // RestTemplate Http = new RestTemplate();

// // headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
// // headers.add("Authorization", "Basic " + encodedCredentials);

// // HttpEntity<String> request = new HttpEntity<String>(headers);

// // ResponseEntity<String> response = Http.exchange(tokenUrl, HttpMethod.POST,
// // request, String.class);

// // System.out.println("Access Token Response ---------" +
// response.getBody());

// // return null;

// OAuthClient client = new OAuthClient(new URLConnectionClient());
// OAuthClientRequest request = OAuthClientRequest.tokenLocation(tokenUrl)
// .setGrantType(GrantType.CLIENT_CREDENTIALS)
// .buildBodyMessage();

// String credential = Base64.getEncoder()
// .encodeToString((clientId + ":" +
// clientSecret).getBytes(StandardCharsets.UTF_8));

// request.addHeader("Accept", "application/json");
// request.addHeader("Content-Type", "application/json");
// request.addHeader("Authorization", "Basic" + credential);

// OAuthJSONAccessTokenResponse res = client.accessToken(request,
// OAuth.HttpMethod.POST,
// OAuthJSONAccessTokenResponse.class);

// String token = res.getAccessToken();

// // HttpHeaders headers = new HttpHeaders();
// // headers.setContentType(MediaType.APPLICATION_JSON);
// // // headers.setBearerAuth("AIzaSyBmjqlP8a9CNAZJxnpFCajQnyGEByTzSqc");
// // // headers.add("X-goog-api-key",
// "AIzaSyBmjqlP8a9CNAZJxnpFCajQnyGEByTzSqc");
// // headers.add("Authorization", "Bearer gcloud auth print-access-token");
// // headers.add("x-goog-user-project", "agent1-avpp");

// // HttpEntity<String> request = new HttpEntity<String>(intent.toString(),
// // headers);

// // String url = TargetUrl.DF_INTENT;

// // ResponseEntity<String> result = Http.postForEntity(url, request,
// // String.class);
// System.out.println(token);
// return token;
// } catch (Exception e) {
// throw new RuntimeException(e.getMessage());
// }
// }
// }
