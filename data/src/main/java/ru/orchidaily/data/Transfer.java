package ru.orchidaily.data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zera
 * @date: 6/16/18 12:36 PM
 */
public class Transfer<T extends Serializable> {
    private String id;
    private String message;
    private T object;

    public Transfer(String message, T object) {
        this.message = message;
        this.object = object;
    }

    public Transfer(String message) {
        this(message, null);
    }

    public String getId() {
        return id;
    }

    public void setId(String pk) {
        this.id = pk;
    }

    public String getMessage() {
        return message;
    }

    public T getObject() {
        return object;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setObject(T object) {
        this.object = object;
    }
}
