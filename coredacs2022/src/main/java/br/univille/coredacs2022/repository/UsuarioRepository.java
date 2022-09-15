package br.univille.coredacs2022.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.univille.coredacs2022.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long>{
    
    Optional<Usuario> findByUsuario(String usuario);
    Optional<Usuario> findByUsuarioAndSenha(String usuario, String senha);
}
