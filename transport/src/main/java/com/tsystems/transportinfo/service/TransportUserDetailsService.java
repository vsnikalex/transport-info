package com.tsystems.transportinfo.service;

import com.tsystems.transportinfo.config.security.TransportUserPrincipal;
import com.tsystems.transportinfo.data.dao.AuthGroupDAO;
import com.tsystems.transportinfo.data.dao.UserDAO;
import com.tsystems.transportinfo.data.entity.AuthGroup;
import com.tsystems.transportinfo.data.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
public class TransportUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private AuthGroupDAO authGroupDAO;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;

        try {
            user = userDAO.findByUsername(username);
        } catch (NoResultException e) {
            log.error("User {} not found", username);
            user = null;
        }

        if(null == user){
            throw new UsernameNotFoundException("cannot find username: " + username);
        }
        List<AuthGroup> authGroups = authGroupDAO.findByUsername(username);
        return new TransportUserPrincipal(user, authGroups);
    }

}
