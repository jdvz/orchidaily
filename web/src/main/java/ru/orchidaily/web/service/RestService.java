package ru.orchidaily.web.service;

import org.apache.http.HttpResponse;
import ru.orchidaily.data.Persistent;
import ru.orchidaily.data.Transfer;
import ru.orchidaily.web.util.ResponseTransformer;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zera
 * @date: 6/15/18 10:54 PM
 */
public interface RestService {
    HttpResponse get(final String url,
                     final Map<String, String> headers,
                     final Map<String, String> params) throws Exception;
}
