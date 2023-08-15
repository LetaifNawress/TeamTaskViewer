package TeamTaskViewer.Controller.pub;


import TeamTaskViewer.DTO.RegistrationRequest;
import TeamTaskViewer.Entity.User;
import TeamTaskViewer.service.facade.UserService;
import TeamTaskViewer.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController("userControllerPub")
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserServiceImpl userServiceImpl;
    @PostMapping("/sign-in/")
    public String signIn(@RequestBody User user) {
        return userService.signIn(user);
    }

    @PostMapping(path = "/register/")
    @ResponseStatus(HttpStatus.CREATED)
    public User register(@RequestBody RegistrationRequest request) throws Exception {
        return userServiceImpl.register(request);
    }



}
