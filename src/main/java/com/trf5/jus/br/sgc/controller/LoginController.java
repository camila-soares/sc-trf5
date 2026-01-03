package com.trf5.jus.br.sgc.controller;

import com.trf5.jus.br.sgc.domain.dto.login.UsuarioLogadoDTO;
import com.trf5.jus.br.sgc.domain.dto.login.AuthenticaDTO;
import com.trf5.jus.br.sgc.service.impl.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
public class LoginController {

   private final LoginService loginService;


    @PostMapping
    public ResponseEntity<UsuarioLogadoDTO> autenticar(@RequestBody AuthenticaDTO dto) throws Throwable {

        UsuarioLogadoDTO response = loginService.login(dto);
        return ResponseEntity.ok(response);
    }


}
