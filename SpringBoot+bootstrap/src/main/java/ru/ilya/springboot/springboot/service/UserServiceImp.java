package ru.ilya.springboot.springboot.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ilya.springboot.springboot.dao.UserDAO;
import ru.ilya.springboot.springboot.entities.User;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImp implements UserService {

    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImp(UserDAO userDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> allUser() {
        return userDAO.allUser();
    }

    @Override
    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.addUser(user);
    }

    @Override
    public User getUserById(long id) {
        return userDAO.getUserById(id);
    }

    @Override
    public void updateUser(User user) {
        String oldPassword = getUserById(user.getId()).getPassword();
        String newPassword = user.getPassword();
        if(!oldPassword.equals(newPassword)) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
       userDAO.updateUser(user);
    }

    @Override
    public void removeUserById(long id) {
        userDAO.removeUserById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDAO.getUserByNickname(s);
        if(user == null) {
            throw new UsernameNotFoundException("User with name" + s);
        }
        return user;
    }
}
