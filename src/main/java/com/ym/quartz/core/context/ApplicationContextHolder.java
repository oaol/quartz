package com.ym.quartz.core.context;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author ShenHuaJie
 * @version 2017年12月6日 上午11:53:31
 */
@Configuration
public class ApplicationContextHolder {

	@Autowired
    static ApplicationContext applicationContext;

    public static <T> T getBean(Class<T> t) {
        return applicationContext.getBean(t);
    }

    public static <T> Map<String, T> getBeansOfType(Class<T> t) {
        return applicationContext.getBeansOfType(t);
    }

    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }
}
