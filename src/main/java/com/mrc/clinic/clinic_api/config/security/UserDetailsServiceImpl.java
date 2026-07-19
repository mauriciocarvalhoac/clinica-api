package com.mrc.clinic.clinic_api.config.security;

import com.mrc.clinic.clinic_api.entity.Usuario;
import com.mrc.clinic.clinic_api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UsuarioRepository repository;

    /// Esta classe carrega o usuário do banco de dados (representado aqui por um metodo fictício) e o converte em um objeto que o Spring Security entende.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Toda requisição passa por aqui.");
        Usuario user = repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username/Password incorretos."));
        return User.withUsername(user.getUsername()).password(user.getPassword()).build();
    }
}
