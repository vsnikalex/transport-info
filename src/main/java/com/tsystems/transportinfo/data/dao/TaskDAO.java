package com.tsystems.transportinfo.data.dao;

import com.tsystems.transportinfo.data.entity.Task;

import java.util.List;

public interface TaskDAO {
    List<Task> findTasksByDriverId(Long id);
}
