package com.tsystems.transportinfo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan({"com.tsystems.transportinfo.aspect",
        "com.tsystems.transportinfo.service", "com.tsystems.transportinfo.data.dao"})
public class AspectConfig {

}
