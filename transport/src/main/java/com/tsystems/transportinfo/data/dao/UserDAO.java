package com.tsystems.transportinfo.data.dao;

import com.tsystems.transportinfo.data.entity.User;

public interface UserDAO {

    User findByUsername(String username);

}
