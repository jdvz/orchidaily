package ru.orchidaily.web.service.impl;

import org.apache.http.HttpResponse;
import org.apache.http.impl.client.BasicCookieStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import ru.orchidaily.web.data.ProductData;
import ru.orchidaily.web.data.transfer.ProductDataTransfer;
import ru.orchidaily.web.data.transfer.ProductListDataTransfer;
import ru.orchidaily.web.service.ProductService;
import ru.orchidaily.web.service.RestService;
import ru.orchidaily.web.service.UrlProviderService;
import ru.orchidaily.web.util.AbstractTransformUtil;
import ru.orchidaily.web.util.DefaultObjectResponseTransformer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zera
 * @date: 6/16/18 11:26 PM
 */
@Service
@Qualifier("productService")
public class DefaultProductService implements ProductService {
    private static final Logger log = LoggerFactory.getLogger(DefaultProductService.class);
    
    private final RestService restService;
    private final UrlProviderService urlProviderService;

    @Autowired
    public DefaultProductService(RestService restService, UrlProviderService urlProviderService) {
        this.restService = restService;
        this.urlProviderService = urlProviderService;
    }

    @Override
    @NonNull
    public ProductDataTransfer getProductById(final String id) {
        try {
            String url = urlProviderService.url("product");
            HttpResponse response = restService.get(url,
                    new HashMap<>(),
                    Collections.singletonMap("id", id));
            return AbstractTransformUtil
                    .transformResponse(url, DefaultObjectResponseTransformer.getProductTransformer(), response)
                    .orElseGet(() -> new ProductDataTransfer("transformer error"));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ProductDataTransfer(e.getMessage());
        }
    }

    @Override
    @NonNull
    public ProductListDataTransfer getProducts(final int page, final int size) {
        try {
            String url = urlProviderService.url("products");
            HttpResponse response = restService.get(url,
                    new HashMap<>(),
                    new HashMap<String, String>() {{
                        put("page", "" + page);
                        put("size", "" + size);
                    }});
            return AbstractTransformUtil
                    .transformResponse(url, DefaultObjectResponseTransformer.getProductListTransformer(), response)
                    .orElseGet(() -> new ProductListDataTransfer("transformer error"));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ProductListDataTransfer(e.getMessage());
        }
    }
}
