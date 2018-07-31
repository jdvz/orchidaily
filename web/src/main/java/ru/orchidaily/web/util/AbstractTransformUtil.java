package ru.orchidaily.web.util;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import ru.orchidaily.data.Transfer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zera
 * @date: 6/16/18 11:37 PM
 */
public class AbstractTransformUtil {
    private static final Logger log = LoggerFactory.getLogger(AbstractTransformUtil.class);

    private AbstractTransformUtil() {
        // prevent instantiation
    }

    @NonNull
    public static <P extends Serializable, T extends Transfer<P>>
    Optional<T> transformResponse(String url, ResponseTransformer<P, T> transformer, HttpResponse response) throws IOException {
        int responseCode = response.getStatusLine().getStatusCode();
        if (HttpStatus.SC_OK <= responseCode && HttpStatus.SC_ACCEPTED >= responseCode) {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()))) {
                String inputLine;
                StringBuilder result = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    result.append(inputLine);
                }

                //print result
                if (log.isDebugEnabled()) {
                    log.debug(result.toString());
                }
                return Optional.ofNullable(transformer.transform(result.toString()));
            }
        } else {
            log.error(String.format("Incorrect response status (%d) from %s", responseCode, url));
        }
        return Optional.empty();
    }
}
