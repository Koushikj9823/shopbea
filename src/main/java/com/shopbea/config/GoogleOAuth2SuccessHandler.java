package com.shopbea.config;

import com.shopbea.model.Role;
import com.shopbea.model.User;
import com.shopbea.repository.RoleRepository;
import com.shopbea.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class GoogleOAuth2SuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        //check if user already exists
        String email = token.getPrincipal().getAttributes().get("email").toString();
        if(!userRepository.findUserByEmail(email).isPresent()){
            User newUser = new User();
            newUser.setFirstName(token.getPrincipal().getAttributes().get("given_name").toString());
            newUser.setLastName(token.getPrincipal().getAttributes().get("family_name").toString());
            newUser.setEmail(email);
            List<Role> role = new ArrayList<>();
            role.add(roleRepository.findById(2).get());
            newUser.setRoles(role);
            userRepository.save(newUser);

        }
        redirectStrategy.sendRedirect(httpServletRequest,httpServletResponse,"/");
    }

}
