package it.unicam.cs.ids.justmeet.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


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
