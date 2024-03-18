package utm.md.util;

import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.socket.ConnectionSocketFactory;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.core5.http.URIScheme;
import org.apache.hc.core5.http.config.Registry;
import org.apache.hc.core5.http.config.RegistryBuilder;
import org.apache.hc.core5.ssl.SSLContexts;
import org.apache.hc.core5.ssl.TrustStrategy;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.security.cert.X509Certificate;

public class RequestUtil {
    private static final String VIBER_AUTH_HEADER = "X-Viber-Auth-Token";
    public static final String TELEGRAM_CHAT_ID = "chat_id";
    public static final String TELEGRAM_MESSAGE_TEXT = "text";

    private RequestUtil() {}

    public static HttpHeaders getHttpHeaders(String headerValue) {
        var headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.set(VIBER_AUTH_HEADER, headerValue);
        return headers;
    }

    public static RestTemplate getRestTemplate() {
        return new RestTemplate(getRequestFactory());
    }

    private static HttpComponentsClientHttpRequestFactory getRequestFactory() {
        try {
            TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
            var sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
            Registry<ConnectionSocketFactory> socketRegistry = RegistryBuilder
                .<ConnectionSocketFactory>create()
                .register(URIScheme.HTTPS.getId(), new SSLConnectionSocketFactory(sslContext))
                .build();
            var httpClient = HttpClientBuilder
                .create()
                .setConnectionManager(new PoolingHttpClientConnectionManager(socketRegistry))
                .setConnectionManagerShared(true)
                .build();

            var requestFactory = new HttpComponentsClientHttpRequestFactory();
            requestFactory.setHttpClient(httpClient);
            requestFactory.setConnectTimeout(20000);
            requestFactory.setConnectionRequestTimeout(20000);
            return requestFactory;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        throw new RuntimeException("ApiClient getRequestFactory Exception !!!");
    }
}
