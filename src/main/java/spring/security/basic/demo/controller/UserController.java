package spring.security.basic.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.security.basic.demo.dto.AuthRequest;
import spring.security.basic.demo.service.UserService;

@RestController
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody AuthRequest authRequest){
        userService.register(authRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("hello")
    public ResponseEntity<?> hello(){
        return ResponseEntity.ok("Hello");
    }

}
