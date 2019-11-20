package com.tsystems.transportinfo.config.security;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

    public SecurityWebApplicationInitializer() {
        super(SecurityJavaConfig.class, CustomAuthenticationSuccessHandler.class);
    }

}
