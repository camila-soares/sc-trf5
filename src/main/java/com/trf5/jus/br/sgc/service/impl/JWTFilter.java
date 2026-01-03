package com.trf5.jus.br.sgc.service.impl;

import com.trf5.jus.br.sgc.util.Util;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.rmi.RemoteException;

@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {


    private  final JWTService jwtService;
    private final LoginService loginService;




    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");

        if(authorization != null && authorization.startsWith("Bearer")) {
            String token  = authorization.split(" ")[1];
            boolean isValid = jwtService.tokenValido(token);
            if(isValid) {
                UserDetails usuario = null;
                usuario = loginService.loadUserByUsername(token);
                if(usuario == null) {
                    throw new RemoteException(Util.getPropMensagem().getString("usuario.erro.naoencontrado"));
                }
                // indicar que se trata de uma autenticação de uma aplicação web
                UsernamePasswordAuthenticationToken user = new
                        UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                user.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                //injeta o usuario dentro do contexto do spring security
                SecurityContextHolder.getContext().setAuthentication(user);
                String secaoUsuario = jwtService.obterOrgao(token);
                request.getSession().setAttribute("orgao", secaoUsuario);
            }
        }

        filterChain.doFilter(request, response);


    }
}
