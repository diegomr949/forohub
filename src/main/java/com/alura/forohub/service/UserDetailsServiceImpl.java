package com.alura.forohub.service;

import com.alura.forohub.model.Usuario;
import com.alura.forohub.repository.UsuarioRepository;
import com.alura.forohub.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = repository.findByUsername(username)
                .orElseThrow();

        UserDetails userDetails = UserDetailsImpl.build(usuario);

        return userDetails;
    }


}
