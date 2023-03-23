package com.example.testing.Service;

import com.example.testing.Repository.UserRepository;
import com.example.testing.model.User;
import jakarta.security.auth.message.AuthException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

@Service
@Transactional
public class ServiceInclude {

    UserService userService;
    UserRepository userRepository;
    public User validateUser(String email, String password) throws AuthException {
        if (email != null)
            email = email.toLowerCase();
        try {
            User userFound = userService.getUserByEmail(email) ;
            if (!BCrypt.checkpw(password, userFound.getPassword())) {
                throw new AuthException("Invalid email or password");
            }


            return userFound;

        } catch (Exception e) {
            throw new AuthException("Invalid email or password");
        }
    }

    public User registerUser(User user) throws AuthException {
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        String email = user.getEmail();
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10));
        user.setPassword(hashedPassword);
        if (email != null)
            email = email.toLowerCase();
        if (!pattern.matcher(email).matches())
            throw new AuthException("Invalid email format");
        long count = userRepository.getCountByEmail(email);
        if (count > 0)
            throw new AuthException("Email already in use");
        return userService.saveUsers(user);
    }



}
