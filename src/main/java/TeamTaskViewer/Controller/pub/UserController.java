package TeamTaskViewer.Controller.pub;


import TeamTaskViewer.DTO.LoggedUserResponse;
import TeamTaskViewer.DTO.LoginRequest;
import TeamTaskViewer.DTO.RegistrationRequest;
import TeamTaskViewer.Entity.User;
import TeamTaskViewer.Repository.UserDao;
import TeamTaskViewer.service.facade.UserService;
import TeamTaskViewer.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController("userControllerPub")
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private UserDao userDao;
    @PostMapping("/sign-in/")
    public String signIn(@RequestBody String requestJson) {
        return userService.signIn(requestJson);
    }

    @PostMapping(path = "/register/")
    @ResponseStatus(HttpStatus.CREATED)
    public User register(@RequestBody RegistrationRequest request) throws Exception {
        return userServiceImpl.register(request);
    }


    @GetMapping("/getRole/{id}")
    public Collection<? extends GrantedAuthority> fetchRole(@PathVariable Long id) {
        return userService.getUserRoles(id);
    }
    @PostMapping("/login")
    public ResponseEntity<LoggedUserResponse> login(@RequestBody LoginRequest loginForm) {
        User user = userDao.findByUsername(loginForm.getUsername());

        if (user != null) {
            LoggedUserResponse response = new LoggedUserResponse();
            response.setUser(user);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
