package TeamTaskViewer.Controller.employee;



import TeamTaskViewer.Entity.User;
import TeamTaskViewer.service.facade.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController("userControllerEMPLOYEE")
@RequestMapping("/employee")
public class UserController {

    @PostMapping("/")
    public User save(@RequestBody User user) {
        return userService.save(user);
    }

    @PostMapping("/sign-in/")
    public String signIn(@RequestBody User user) {
        return userService.signIn(user);
    }


    @GetMapping("/username/{username}")
    public UserDetails loadUserByUsername(@PathVariable String username) throws UsernameNotFoundException {
        return userService.loadUserByUsername(username);
    }


    private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
}
