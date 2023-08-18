package TeamTaskViewer.service.impl;


import TeamTaskViewer.DTO.RegistrationRequest;
import TeamTaskViewer.Entity.Role;
import TeamTaskViewer.Entity.User;
import TeamTaskViewer.Repository.RoleDao;
import TeamTaskViewer.Repository.UserDao;
import TeamTaskViewer.service.facade.RoleService;
import TeamTaskViewer.service.facade.UserService;
import TeamTaskViewer.service.util.JwtUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired private UserDao userDao;
    @Autowired private RoleService roleService;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private JwtUtil jwtUtil;
    @Autowired private RoleDao roleDao;
    @Autowired private UserService userService;
    @Override
    public String signIn(String requestJson) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, String> requestMap = objectMapper.readValue(requestJson, new TypeReference<Map<String, String>>() {});

            String username = requestMap.get("username");
            String password = requestMap.get("password");

            // Charge les détails de l'utilisateur depuis la base de données
            UserDetails userDetails = loadUserByUsername(username);

            // Vérifie si le mot de passe fourni correspond au mot de passe stocké (hashé) dans la base de données
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("Bad credentials");
            }

            // Authentifie l'utilisateur
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Génère le token JWT
            String token = jwtUtil.generateToken(userDetails);
            return token;

        } catch (IOException | UsernameNotFoundException | BadCredentialsException ex) {
            throw new BadCredentialsException("Bad credentials", ex);
        }
    }




    @Override
    public User save(User user) {
        User loadedUser = userDao.findByUsername(user.getUsername());
        if (loadedUser != null)
            return null;
        else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            roleService.save(user.getAuthorities());
            userDao.save(user);
            return user;
        }
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public Collection<? extends GrantedAuthority> getUserRoles(Long userId) {
        return null;
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if (user == null || user.getId() == null) {
            throw new UsernameNotFoundException("user " + username + " not founded");
        } else {
            return user;
        }
    }
    public User register(RegistrationRequest registerRequest) throws Exception {
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setUsername(registerRequest.getUsername());
        user.setPassword(registerRequest.getPassword());
        user.setAuthorities(Arrays.asList(new Role("EMPLOYEE")));
        user.setEnabled(true);

        // Enregistrer l'utilisateur dans la base de données
        this.save(user);



        return user;
    }

}
