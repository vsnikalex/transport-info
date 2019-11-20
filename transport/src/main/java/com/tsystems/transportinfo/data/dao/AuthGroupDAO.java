package com.tsystems.transportinfo.data.dao;

import com.tsystems.transportinfo.data.entity.AuthGroup;

import java.util.List;

public interface AuthGroupDAO {

    List<AuthGroup> findByUsername(String username);

}
