package com.tsystems.transportinfo.config;

import com.tsystems.transportinfo.config.jms.JmsConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class WebServletConfig implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext ctx) {
        AnnotationConfigWebApplicationContext webCtx = new AnnotationConfigWebApplicationContext();
        webCtx.register(ViewConfig.class, HibernateConfig.class, JmsConfig.class);
        webCtx.setServletContext(ctx);

        ServletRegistration.Dynamic servlet = ctx.addServlet("dispatcher", new DispatcherServlet(webCtx));
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");
    }

}
