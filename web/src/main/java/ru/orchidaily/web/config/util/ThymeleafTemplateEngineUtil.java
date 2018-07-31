package ru.orchidaily.web.config.util;

import org.thymeleaf.TemplateEngine;

import javax.servlet.ServletContext;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zera
 * @date: 6/16/18 11:12 PM
 */
public class ThymeleafTemplateEngineUtil {
    private static final String TEMPLATE_ENGINE_ATTR = "com.thymeleafexamples.thymeleaf3.TemplateEngineInstance";

    private ThymeleafTemplateEngineUtil() {
        // prevent instantiation
    }

    public static void storeTemplateEngine(ServletContext context, TemplateEngine engine) {
        context.setAttribute(TEMPLATE_ENGINE_ATTR, engine);
    }

    public static TemplateEngine getTemplateEngine(ServletContext context) {
        return (TemplateEngine) context.getAttribute(TEMPLATE_ENGINE_ATTR);
    }
}
