package com.etsisi.sps.services;

import com.etsisi.sps.daos.UserDao;
import com.etsisi.sps.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao dao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User findById(int id) {
        return dao.findById(id);
    }

    public User findByUsername(String username) {
        User user = dao.findByUsername(username);
        return user;
    }

    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        dao.save(user);
    }

    /*
     * Since the method is running with Transaction, No need to call hibernate update explicitly.
     * Just fetch the entity from db and update it with proper values within transaction.
     * It will be updated in db once transaction ends.
     */
    public void updateUser(User user) {
        User entity = dao.findById(user.getId());
        if (entity != null) {
            entity.setUsername(user.getUsername());
            if (!user.getPassword().equals(entity.getPassword())) {
                entity.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            entity.setFirstName(user.getFirstName());
            entity.setLastName(user.getLastName());
            entity.setEmail(user.getEmail());
            entity.setUserRoles(user.getUserRoles());
        }
    }


    public void deleteUserByUsername(String username) {
        logger.debug("Service.deleteUserByUsername {}",username);
        dao.deleteByUsername(username);
    }

    public List<User> findAllUsers() {
        return dao.findAllUsers();
    }

    public boolean isUserUsernameUnique(Integer id, String username) {
        User user = findByUsername(username);
        return (user == null || ((id != null) && (user.getId() == id)));
    }

    @Override
    public boolean isUserEmailValid(String email) {
        Pattern pattern;
        Matcher matcher;

        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
