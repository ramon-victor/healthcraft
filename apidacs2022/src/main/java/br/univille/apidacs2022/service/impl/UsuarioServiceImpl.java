package br.univille.apidacs2022.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.univille.coredacs2022.repository.UsuarioRepository;

public class UsuarioServiceImpl implements UserDetailsService {


    @Autowired
    private UsuarioRepository repository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var resulCons = repository.findByUsuario(username);
        if(resulCons.isPresent()) {
            var usuario = resulCons.get();
            return new User(usuario.getUsuario(), usuario.getSenha(), new ArrayList<>());
        }
        return null;
    }
    
}
