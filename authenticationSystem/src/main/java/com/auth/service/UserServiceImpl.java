package com.auth.service;

import com.auth.dao.RoleDao;
import com.auth.dao.UserDao;
import com.auth.model.User;
import com.auth.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {

        User local = this.userDao.findByUsername(user.getUsername());

        if(local!= null)
        {
            System.out.println("User already present");
            throw new Exception("User already present");
        }
        else {
            for(UserRole ur : userRoles)
            {
                roleDao.save(ur.getRole());
            }
            user.getUserRoles().addAll(userRoles);
            local = this.userDao.save(user);
        }
        return local;
    }

    @Override
    public User getUserByUsername(String username) {
        return this.userDao.findByUsername(username);
    }

    @Override
    public void deleteUser(Long userId) {
        this.userDao.deleteById(userId);
    }

    @Override
    public User updateUser(User user) {
        return null;
    }
}

