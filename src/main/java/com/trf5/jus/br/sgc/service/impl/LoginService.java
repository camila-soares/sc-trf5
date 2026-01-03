package com.trf5.jus.br.sgc.service.impl;

import com.trf5.jus.br.sgc.domain.dto.login.AuthenticaDTO;
import com.trf5.jus.br.sgc.domain.dto.login.RetornoAutenticarCompleto;
import com.trf5.jus.br.sgc.domain.dto.login.UsuarioDTO;
import com.trf5.jus.br.sgc.domain.dto.login.UsuarioLogadoDTO;
import com.trf5.jus.br.sgc.domain.entity.Usuario;
import com.trf5.jus.br.sgc.service.AutenticarCompletoDws;
import com.trf5.jus.br.sgc.util.Util;
import com.trf5.jus.br.sgc.webservice.sip.EncryptDws;
import io.jsonwebtoken.Claims;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;


@Service
@Transactional
@RequiredArgsConstructor
public class LoginService implements UserDetailsService {

    private final JWTService jwtService;
    //private final UsuarioRepository usuarioRepository;
    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;

    public UsuarioLogadoDTO login(AuthenticaDTO usuarioDTO) throws Throwable {

        Usuario usuario = getUsuarioOrgao(usuarioDTO.getLogin(), usuarioDTO.getSecao());
        if (usuario == null) {
            throw new Throwable(Util.getPropMensagem().getString("usuario.erro.naoencontrado"));
        } else if (usuario.getSenha() != null && !passwordEncoder.matches(usuarioDTO.getSenha(), usuario.getSenha())) {
            throw new Throwable(Util.getPropMensagem().getString("usuario.erro.senha.invalida"));
        }
    
         // Autenticao via SIP
         //autenticar(usuarioDTO.getLogin(), usuarioDTO.getSenha(), usuarioDTO.getSecao());
       
        
        if(usuario.getSenha() == null) {
            usuario.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
            
        }
        
            usuario.setToken(jwtService.gerarToken(usuario));
           Claims claims = jwtService.obterClaims(usuario.getToken());

            AtomicReference<List<String>> strings = new AtomicReference<>(new ArrayList<>());

            strings.get().add(claims.get("roles").toString());
           usuario.setRoles(strings.get().toArray(new String[0]));
           usuario = this.usuarioService.salve(usuario).getBody();

            return UsuarioLogadoDTO.builder().login(usuario.getLogin()).token(usuario.getToken()).build();
        }

        private RetornoAutenticarCompleto autenticar(String login, String senha, String secao) {

        EncryptDws encryptDws = new EncryptDws();

        AutenticarCompletoDws autenticarCompletoDws = new AutenticarCompletoImpl();

        try {
            String senha64 = encryptDws.traduzir(senha);
            RetornoAutenticarCompleto retornoAutCompleto = autenticarCompletoDws.autenticar(secao, login, senha64);
             if (retornoAutCompleto != null) {
                 return retornoAutCompleto;
             } else {
                throw new RuntimeException(Util.getPropMensagem().getString("login.erro"));
            }

        } catch (Exception ne) {
            throw new RuntimeException(ne.getMessage());
        }

   }



    @Override
    public UserDetails loadUserByUsername(String token) throws RuntimeException {
        String secaoUsuario = jwtService.obterOrgao(token);
        String loginUsuario = null;

        try {
            loginUsuario = jwtService.obterLoginUsuario(token);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        Usuario usuario = getUsuarioOrgao(loginUsuario, secaoUsuario);

        String[] roles = this.definirPermissaoUsuario();
        return User.builder().username(usuario.getLogin()).password("XXXX").roles(roles).build();

    }


    private String[] definirPermissaoUsuario() {
        List<String> listaPermissoes = new ArrayList<String>();
        listaPermissoes.add("ADMIN");
        return listaPermissoes.toArray(String[]::new);
    }

    public Usuario getUsuario(String login) {
        return usuarioService.consultarPorLogin(login);
    }

    public Usuario getUsuarioOrgao(String login, String orgao) {
        return usuarioService.getByLoginOrgao(login, orgao);
    }



}