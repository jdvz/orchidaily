package ru.orchidaily.web.service.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.orchidaily.web.service.UrlProviderService;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zera
 * @date: 6/15/18 11:16 PM
 */
@Service
@Qualifier("urlProviderService")
public class SimpleUrlProviderService implements UrlProviderService {
    @Override
    public String url(final String name) {
        return "http://localhost:8080/core/" + name + "/";
    }
}
