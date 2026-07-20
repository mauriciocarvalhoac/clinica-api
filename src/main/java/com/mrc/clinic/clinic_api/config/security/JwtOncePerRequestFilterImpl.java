package com.mrc.clinic.clinic_api.config.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtOncePerRequestFilterImpl extends OncePerRequestFilter {
    @Autowired
    private UserDetailsServiceImpl autenticacaoService;

    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("Todas as requisições passam por essa filtrgem (JwtOncePerRequestFilterImpl)");
        String authorization = request.getHeader("Authorization");
        if (authorization != null) {
            String token = authorization.replace("Bearer ", "");
            if (token != null) {
                String login = tokenService.validarToken(token);
                if (!login.isEmpty()) {
                    UserDetails usuario = autenticacaoService.loadUserByUsername(login);
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
