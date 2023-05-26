package com.server.demo;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping("/")
    public User save(@RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        return userService.save(user);
    }
    
    @PatchMapping("/{id}/password")
    public User changePassword(@PathVariable Long id, @RequestBody PasswordDto passwordDto) {
        User user = userService.findById(id);
        user.setPassword(passwordDto.getPassword());
        return userService.save(user);
    }
}
