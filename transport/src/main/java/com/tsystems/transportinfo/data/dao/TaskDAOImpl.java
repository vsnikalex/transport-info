package com.tsystems.transportinfo.data.dao;

import com.tsystems.transportinfo.data.entity.*;
import com.tsystems.transportinfo.data.entity.Driver_;
import com.tsystems.transportinfo.data.entity.Task_;
import com.tsystems.transportinfo.data.entity.enums.DriverAction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

@Repository
public class TaskDAOImpl implements TaskDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Task> findTasksByDriverId(Long id) {
        Session session = sessionFactory.getCurrentSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Task> cq = cb.createQuery(Task.class);

        Root<Task> task = cq.from(Task.class);
        Join<Task, Driver> driver = task.join(Task_.driver);

        cq.select(task).where(cb.equal(driver.get(Driver_.id), id));

        Query<Task> query = session.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public void startTask(DriverAction action, LocalDateTime start, Long driverId, Long truckId) {
        Task task = new Task();
        task.setAction(action);
        task.setStart(start);
        task.setDriver(new Driver(driverId));
        task.setTruck(new Truck(truckId));

        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(task);
    }

    @Override
    public void finishCurrentTask(Long driverId, LocalDateTime end) {
        Session session = sessionFactory.getCurrentSession();
        Stream<Task> tasks = session.createQuery("SELECT t FROM Task t", Task.class).stream();

        Task task = tasks.filter(t -> t.getDriver().getId() == driverId && (t.getEnd() == null))
                         .findFirst()
                         .orElse(null);

        if (task != null) {
            task.setEnd(end);
            session.update(task);
        }
    }

    @Override
    public int calculateDrivingDrivers() {
        Session session = sessionFactory.getCurrentSession();
        Stream<Task> tasks = session.createQuery("SELECT t FROM Task t", Task.class).stream();

        return (int) tasks.filter(task -> (task.getEnd() == null) && task.getAction().equals(DriverAction.DRIVE))
                          .count();
    }
}
