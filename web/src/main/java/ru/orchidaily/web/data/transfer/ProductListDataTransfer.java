package ru.orchidaily.web.data.transfer;

import ru.orchidaily.data.Transfer;
import ru.orchidaily.web.data.ProductData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zera
 * @date: 6/16/18 3:27 PM
 */
public class ProductListDataTransfer extends Transfer<ArrayList<ProductData>> {
    private int totalPages;
    private long totalElements;

    public ProductListDataTransfer(String message, ArrayList<ProductData> object) {
        super(message, object);
    }

    public ProductListDataTransfer(String message) {
        super(message);
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public List<ProductData> getContent() {
        return getObject();
    }

    public void setContent(Collection<ProductData> content) {
        this.setObject(new ArrayList<ProductData>() {{
            addAll(content);
        }});
    }

    public void setContent(ArrayList<ProductData> content) {
        this.setObject(content);
    }
}
