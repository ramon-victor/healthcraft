package br.univille.apidacs2022;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={
	"br.univille.coredacs2022","br.univille.apidacs2022"})
public class Apidacs2022Application {

	public static void main(String[] args) {
		SpringApplication.run(Apidacs2022Application.class, args);
	}

}
