package br.univille.dacs2022;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={
	"br.univille.coredacs2022","br.univille.dacs2022"})
public class Dacs2022Application {

	public static void main(String[] args) {
		SpringApplication.run(Dacs2022Application.class, args);
	}
}