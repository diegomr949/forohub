package com.alura.forohub.security;

import com.alura.forohub.model.Usuario;
import com.alura.forohub.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


import java.io.IOException;

public class AutenticacionViaTokenFilter extends BasicAuthenticationFilter {

    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;

    public AutenticacionViaTokenFilter(AuthenticationManager authenticationManager, TokenService tokenService, UsuarioRepository usuarioRepository) {
        super(authenticationManager);
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String token = obtenerToken(request);
        if (token != null && tokenService.validateToken(token)) {
            autenticarCliente(token);
        }
        chain.doFilter(request, response);
    }

    private void autenticarCliente(String token) {
        String username = tokenService.getUsernameFromToken(token);
        Usuario usuario = usuarioRepository.findByUsername(username).orElseThrow(); // Obtener usuario por username
        UserDetails userDetails = UserDetailsImpl.build(usuario);
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String obtenerToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            return null;
        }
        return token.substring(7);
    }
}
