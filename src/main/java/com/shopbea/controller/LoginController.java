package com.shopbea.controller;

import com.shopbea.global.GlobalData;
import com.shopbea.model.Role;
import com.shopbea.model.User;
import com.shopbea.repository.RoleRepository;
import com.shopbea.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/login")
    public String login(){
        GlobalData.cart.clear();
        return "login";
    }
    @GetMapping("/register")
    public String registerGet(){
        return "register";
    }
    @PostMapping("/register")
    public String postRegister(@ModelAttribute("user") User user, HttpServletRequest request) throws ServletException {
    String password = user.getPassword();
    user.setPassword(bCryptPasswordEncoder.encode(password));
    List<Role> role = new ArrayList<>();
    role.add(roleRepository.findById(2).get());
    user.setRoles(role);
    userRepository.save(user);

    request.login(user.getEmail(),password);
    return "redirect:/";

    }
}
