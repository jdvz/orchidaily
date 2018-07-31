package ru.orchidaily.web.util;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import ru.orchidaily.data.Persistent;
import ru.orchidaily.data.Transfer;
import ru.orchidaily.web.data.ProductData;
import ru.orchidaily.web.data.transfer.ProductDataTransfer;
import ru.orchidaily.web.data.transfer.ProductListDataTransfer;
import ru.orchidaily.web.service.impl.SimpleRestService;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zera
 * @date: 6/16/18 11:32 PM
 */
public class DefaultObjectResponseTransformer<P extends Serializable, T extends Transfer<P>> implements ResponseTransformer<P, T> {
    private static final Logger log = LoggerFactory.getLogger(DefaultObjectResponseTransformer.class);

    private final Class<T> clazz;

    private static final ResponseTransformer<ProductData, ProductDataTransfer> PRODUCT_TRANSFORMER
            = new DefaultObjectResponseTransformer<>(ProductDataTransfer.class);

    private static final ResponseTransformer<ArrayList<ProductData>, ProductListDataTransfer> PRODUCT_LIST_TRANSFORMER
            = new DefaultObjectResponseTransformer<>(ProductListDataTransfer.class);

    public static ResponseTransformer<ProductData, ProductDataTransfer> getProductTransformer() {
        return PRODUCT_TRANSFORMER;
    }

    public static ResponseTransformer<ArrayList<ProductData>, ProductListDataTransfer> getProductListTransformer() {
        return PRODUCT_LIST_TRANSFORMER;
    }

    public DefaultObjectResponseTransformer(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    @Nullable
    public T transform(final String response) {
        ObjectMapper jsonMapper = new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        jsonMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        jsonMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
        try {
            return jsonMapper.readValue(response, clazz);
        } catch (IOException e) {
            log.error(String.format("Parse response error %s", e.getMessage()), e);
        }
        return null;
    }
}
