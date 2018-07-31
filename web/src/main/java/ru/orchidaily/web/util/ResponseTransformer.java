package ru.orchidaily.web.util;

import ru.orchidaily.data.Persistent;
import ru.orchidaily.data.Transfer;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zera
 * @date: 6/15/18 11:35 PM
 */
public interface ResponseTransformer<P extends Serializable, T extends Transfer<P>>
{
    T transform(final String response);
}