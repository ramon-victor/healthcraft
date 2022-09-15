package br.univille.apidacs2022;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import br.univille.coredacs2022.entity.Usuario;
import br.univille.coredacs2022.repository.UsuarioRepository;

@Component
public class Startup {
    
    @Autowired
    private UsuarioRepository repository;


    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(repository.findByUsuario("admin").isEmpty()){
            var adminUser = new Usuario();
            adminUser.setUsuario("admin");
            adminUser.setSenha("admin");
            repository.save(adminUser);
        }
    }
}
