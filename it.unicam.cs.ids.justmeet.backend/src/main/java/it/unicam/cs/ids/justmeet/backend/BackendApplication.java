package it.unicam.cs.ids.justmeet.backend;

import it.unicam.cs.ids.justmeet.backend.configuration.service.IUserDetailsService;
import it.unicam.cs.ids.justmeet.backend.configuration.service.IUserDetailsServiceImpl;
import it.unicam.cs.ids.justmeet.backend.model.PhysicalUser;
import it.unicam.cs.ids.justmeet.backend.model.UserRole;
import it.unicam.cs.ids.justmeet.backend.model.enumeration.EnumUserRole;
import it.unicam.cs.ids.justmeet.backend.model.intfc.IPhysicalUser;
import it.unicam.cs.ids.justmeet.backend.model.intfc.IUser;
import it.unicam.cs.ids.justmeet.backend.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashSet;
import java.util.Set;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class BackendApplication {

	public static void main(String[] args) {
		/*ApplicationContext ctx = (ApplicationContext)*/SpringApplication.run(BackendApplication.class, args);
		/*UserRepository userRepository = ctx.getBean(UserRepository.class);
		IPhysicalUser prova = new PhysicalUser();
		prova.setUniqueID("ciao@ciao.it");
		prova.setPassword("ciao");
		Set<UserRole> c = new HashSet<UserRole>();
		c.add(new UserRole(EnumUserRole.STD));
		prova.setRole(c);
		System.out.println(prova.hashCode());


		userRepository.save(prova);

		IPhysicalUser prova1 = (IPhysicalUser)userRepository.findById("ciao@ciao.it").get();


		System.out.println(prova1.hashCode());*/
	}
}
