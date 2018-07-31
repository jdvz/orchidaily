package ru.orchidaily.web.data.transfer;

import ru.orchidaily.data.Transfer;
import ru.orchidaily.web.data.ProductData;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zera
 * @date: 6/16/18 3:27 PM
 */
public class ProductDataTransfer extends Transfer<ProductData> {
    public ProductDataTransfer(String message, ProductData object) {
        super(message, object);
    }

    public ProductDataTransfer(String message) {
        super(message);
    }

    public ProductData getProduct() {
        return this.getObject();
    }

    public void setProduct(ProductData product) {
        this.setObject(product);
    }
}
