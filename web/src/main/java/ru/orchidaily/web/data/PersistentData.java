package ru.orchidaily.web.data;

import ru.orchidaily.data.Persistent;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zera
 * @date: 6/16/18 3:24 PM
 */
public abstract class PersistentData<PK extends Serializable> implements Persistent<PK> {
    private PK id;

    public PersistentData() {
    }

    public PersistentData(PK id) {
        this.id = id;
    }

    @Override
    public PK getId() {
        return id;
    }

    public void setId(PK id) {
        this.id = id;
    }
}
