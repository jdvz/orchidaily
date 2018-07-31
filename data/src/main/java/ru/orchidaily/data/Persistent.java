package ru.orchidaily.data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zera
 * @date: 6/15/18 10:33 PM
 */
public interface Persistent<PK extends Serializable> extends Serializable {
    PK getId();
}
