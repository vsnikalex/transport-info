package com.tsystems.transportinfo.config;

import com.tsystems.transportinfo.soap.Notifications;
import com.tsystems.transportinfo.soap.NotificationsServiceLocal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan({"com.tsystems.transportinfo.aspect",
        "com.tsystems.transportinfo.service", "com.tsystems.transportinfo.data.dao"})
public class AspectConfig {

    @Bean
    public Notifications getHttpSoapService() {
        NotificationsServiceLocal notificationsService = new NotificationsServiceLocal();
        return notificationsService.getHttpNotificationsImplPort();
    }

}
