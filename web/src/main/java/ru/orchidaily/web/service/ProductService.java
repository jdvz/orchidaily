package ru.orchidaily.web.service;

import ru.orchidaily.data.Transfer;
import ru.orchidaily.web.data.ProductData;
import ru.orchidaily.web.data.transfer.ProductDataTransfer;
import ru.orchidaily.web.data.transfer.ProductListDataTransfer;
import ru.orchidaily.web.util.ResponseTransformer;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zera
 * @date: 6/16/18 11:24 PM
 */
public interface ProductService {
    ProductDataTransfer getProductById(final String id);

    ProductListDataTransfer getProducts(final int page, final int size);

}
