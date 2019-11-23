package com.tsystems.transportinfo.service;

import com.tsystems.transportinfo.data.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface TransportUserDetailsService extends UserDetailsService {

    void saveUser(User user);

}
