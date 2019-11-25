package com.tsystems.transportinfo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Slf4j
@Configuration
@EnableAspectJAutoProxy
@ComponentScan({"com.tsystems.transportinfo.aspect",
        "com.tsystems.transportinfo.service", "com.tsystems.transportinfo.data.dao"})
public class AspectConfig {

}
