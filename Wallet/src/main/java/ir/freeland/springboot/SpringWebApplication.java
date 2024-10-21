package ir.freeland.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
//@ComponentScan(basePackages = "Service")
public class SpringWebApplication {
	public static void main(String[] args) {
//		SpringApplication.run(SpringWebApplication.class, args);

////////////////////
		ConfigurableApplicationContext context = SpringApplication.run(SpringWebApplication.class, args);
		
//		JpaBaseInRun JpaBaseInRun = context.getBean( JpaBaseInRun.class);
	//	JpaBaseInRun.sampleRun();
		///////////////////////////
	}

}
