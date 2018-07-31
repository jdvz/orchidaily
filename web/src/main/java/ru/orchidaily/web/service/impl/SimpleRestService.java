package ru.orchidaily.web.service.impl;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.ssl.SSLContexts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.orchidaily.web.service.RestService;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zera
 * @date: 6/15/18 11:02 PM
 */
@Service
@Qualifier("restService")
public class SimpleRestService implements RestService {
    private static final Logger log = LoggerFactory.getLogger(SimpleRestService.class.getName());
    
    private static final Pattern HTTPS = Pattern.compile("/https:\\/\\/.*/");

    private final ThreadLocal<CookieStore> store = new ThreadLocal<>();

    public HttpResponse get(final String url,
                            final Map<String, String> headers,
                            final Map<String, String> params) throws URISyntaxException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, IOException {
        final HttpClientContext context = HttpClientContext.create();

        boolean ssl = HTTPS.matcher(url).matches();
        final HttpClient client = createHttpClient(ssl, store.get());

        URIBuilder urlBuilder = new URIBuilder(url);
        params.forEach(urlBuilder::addParameter);

        URI uri = urlBuilder.build();

        BasicClientCookie cookie = new BasicClientCookie("JSESSIONID", "1234");
        cookie.setDomain(uri.getHost());
        cookie.setPath("/");
        store.get().addCookie(cookie);

        HttpGet get = new HttpGet(uri);
        get.addHeader("User-Agent", "Test/1.1");
        headers.forEach(get::addHeader);

        return client.execute(get, context);
    }

    private static CloseableHttpClient createHttpClient(boolean ssl, final CookieStore store)
            throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        return ssl ?
                HttpClientBuilder.create()
                        .setSSLSocketFactory(new SSLConnectionSocketFactory(SSLContexts.custom()
                                .loadTrustMaterial(null, new TrustSelfSignedStrategy() {
                                    @Override
                                    public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                                        return true;
                                    }
                                }).build())
                        )
                        .setSSLHostnameVerifier(new HostnameVerifier() {
                            @Override
                            public boolean verify(String paramString, SSLSession paramSSLSession) {
                                return true;
                            }
                        })
                        .setDefaultCredentialsProvider(credentialProvider())
                        .setDefaultCookieStore(store)
                        .build() :
                HttpClientBuilder.create().setDefaultCookieStore(store).build();
    }

    private static CredentialsProvider credentialProvider() {
        return new BasicCredentialsProvider();
    }

}
