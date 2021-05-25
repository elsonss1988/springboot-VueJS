package com.elson.mycash;

import com.elson.mycash.service.UsuarioService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MycashApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(MycashApplication.class, args);
		UsuarioService service=context.getBean(UsuarioService.class);
		service.registraUsuarioAdmin("admin@mycash.com","admin");
		service.save("user@mycash.com","user" );
	}
	@GetMapping("/ola")
	public String Hello(@RequestParam("nome") String nome){
		return "<h1> Hello "+nome+"!!</h1>";
	}
}
