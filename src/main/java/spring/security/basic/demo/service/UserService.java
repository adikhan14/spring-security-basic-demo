package spring.security.basic.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring.security.basic.demo.dto.AuthRequest;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;

    Map<String, String> users = new ConcurrentHashMap<>();

    @Autowired
    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public void register(AuthRequest authRequest){

        if (users.containsKey(authRequest.getUsername())){
            throw new RuntimeException("User already exists");
        }

        users.put(authRequest.getUsername(), passwordEncoder.encode(authRequest.getPassword()));
    }

    public String getPassword(String username){
        return users.get(username);
    }

}
