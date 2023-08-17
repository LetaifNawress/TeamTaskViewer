package TeamTaskViewer.DTO;

import TeamTaskViewer.Entity.Role;
import TeamTaskViewer.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoggedUserResponse {
    private User user;
    private List<Role> roles;
}
