package TeamTaskViewer;

import TeamTaskViewer.Entity.Role;
import TeamTaskViewer.Entity.User;
import TeamTaskViewer.service.facade.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication

public class TeamTaskViewerApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TeamTaskViewerApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		if(true){
			User admin = new User("admin","admin","llll@nnjnj.com");
			admin.setAuthorities(Arrays.asList(new Role("ADMIN")));
			userService.save(admin);

			User employee = new User("employee","employee","ssfg@hnh.com");
			employee.setAuthorities(Arrays.asList(new Role("EMPLOYEE")));
			userService.save(employee);
		}

	}
	@Autowired
	private UserService userService;



}
