package ru.orchidaily.data.product;

import ru.orchidaily.data.Persistent;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zera
 * @date: 6/15/18 10:34 PM
 */
public interface Product<PK extends Serializable> extends Persistent<PK> {
    String getName();
    String getDescription();
}
