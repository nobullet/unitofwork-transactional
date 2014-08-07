package service;

import com.google.inject.persist.Transactional;
import dao.UserDao;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.inject.Singleton;
import models.User;
import ninja.jpa.UnitOfWork;
import ninja.scheduler.Schedule;
import org.slf4j.Logger;

/**
 * User service.
 */
@Singleton
public class UserService {

    UserDao userDao;
    Logger logger;

    @Inject
    public UserService(UserDao userDao, Logger logger) {
        this.userDao = userDao;
        this.logger = logger;
    }

    public User getUserOneNTNUOW() {
        return userDao.getByName("one");
    }
    
    @UnitOfWork
    public User getUserOneUOW() {
        return userDao.getByName("one");
    }

    @Transactional(rollbackOn = Exception.class)
    public User getUserOneT() {
        return userDao.getByName("one");
    }

    @Transactional(rollbackOn = Exception.class)
    public User useCase() {
        User user = userDao.getByName("one");
        if (user == null) {
            user = new User("one");
            userDao.persist(user);
        } else {
            user.visit();
            userDao.merge(user);
        }
        logger.warn("User one visited: {} times, {} users in database.",
                user.getVisited(),
                userDao.getAllUsers().size());
        return user;
    }

    @Schedule(delay = 1, initialDelay = 1, timeUnit = TimeUnit.SECONDS)
    public void doStuffEachSeconds() {
        useCase();
    }
}
