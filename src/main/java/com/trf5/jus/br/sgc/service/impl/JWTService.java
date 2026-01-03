package com.trf5.jus.br.sgc.service.impl;


import com.trf5.jus.br.sgc.domain.entity.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class JWTService {

    private static final long EXPIRATION_TIME = 959990000;
    private static final String JEy = "IQMeuXPOp5b5/EfsetKsWnJ0wS2RjYPuICbvM7dAMrZS0RDXqFNLSl/jsbGRWCopNW0NEo15MrNBFyjlZ3hmYA==";


    @PostConstruct
    public SecretKey init() {
        byte[] keyBytes = Decoders.BASE64.decode(JEy);
        SecretKey key = Keys.hmacShaKeyFor(keyBytes);
        return  key;
    }

    public String gerarToken(Usuario usuario) {
        LocalDateTime  dataHoraExpiracao = LocalDateTime.now().plusMinutes(EXPIRATION_TIME);
        List<String> permissoes = new ArrayList<String>();
        usuario.getPermissoes().forEach(permissao -> permissoes.add(permissao.getPerfil()));

        String login = usuario.getLogin();

        boolean isUnidadeAtiva = true;
       //adicionar validação extra para esses tipos relacionados com os login
        return Jwts
                .builder()
                .claim("orgao", usuario.getOrgao().getDescricaoOrgao())
                .claim("roles", permissoes.toArray(String[]::new))
                .claim("id", usuario.getId())
                .claim("isUnidadeAtiva", isUnidadeAtiva)
                .setSubject(login)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, init())
                .compact();

    }

    public Claims obterClaims(String token) throws ExpiredJwtException {

        return Jwts.parserBuilder()
                 .setSigningKey(init())
                .build()
                .parseClaimsJws(token)
                .getBody();

    }

    public boolean tokenValido(String token) {
        try {

            Claims claims = this.obterClaims(token);
            Date dataExpiracao = claims.getExpiration();
            LocalDateTime dateTime =  dataExpiracao.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            boolean expirado = LocalDateTime.now().isAfter(dateTime);
            if(!expirado) {
                io.jsonwebtoken.JwtParser parser = Jwts.parser();
                parser.setSigningKey(init());
                parser.parseClaimsJws(token);
                return true;
            }else {
                return false;
            }

        } catch (Exception e) {
            return false;
        }
    }

    public String obterLoginUsuario(String token)  throws Exception{
        return (String) this.obterClaims(token).getSubject();
    }

    public String obterOrgao(String token)  throws ExpiredJwtException {
        return (String) this.obterClaims(token).get("orgao");
    }

}
