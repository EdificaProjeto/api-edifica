package com.edifica.apiedifica.repositories;

import com.edifica.apiedifica.domain.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    Usuario findByEmail(String email);


}
