package ru.orchidaily.web.data;

import ru.orchidaily.data.product.Product;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zera
 * @date: 6/16/18 3:23 PM
 */
public class ProductData extends PersistentData<String> implements Product<String> {
    private String name;
    private String description;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
