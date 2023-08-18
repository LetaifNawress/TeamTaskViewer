package TeamTaskViewer.service.facade;

import TeamTaskViewer.Entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;
import java.util.List;

public interface UserService extends UserDetailsService {
    String signIn(String requestJson );
    User save(User user);
   List<User> findAll();


    Collection<? extends GrantedAuthority> getUserRoles(Long userId);


}
