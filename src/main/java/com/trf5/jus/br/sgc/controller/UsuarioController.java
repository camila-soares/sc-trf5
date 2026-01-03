package com.trf5.jus.br.sgc.controller;


import com.trf5.jus.br.sgc.domain.dto.RetornoConsultarUsuarioDTO;
import com.trf5.jus.br.sgc.domain.dto.UsuarioResumoDTO;
import com.trf5.jus.br.sgc.domain.dto.login.UsuarioDTO;
import com.trf5.jus.br.sgc.domain.entity.Usuario;
import com.trf5.jus.br.sgc.forms.UsuarioForms;
import com.trf5.jus.br.sgc.repository.UsuarioRepository;
import com.trf5.jus.br.sgc.service.impl.UsuarioService;
import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class UsuarioController {


    private final UsuarioService usuarioService;

    private final UsuarioRepository usuarioRepository;

    @GetMapping
    public Page<UsuarioResumoDTO> listar(@RequestParam(value = "todos", required = false) Boolean todos,
                                         @PageableDefault(sort = "nome", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable paginacao) {
        return usuarioService.listar(todos, paginacao);
    }

    @GetMapping("orgao/{orgao}")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
//                    value = "Pagina a ser carregada", defaultValue = "0"),
//            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
//                    value = "Quantidade de registros", defaultValue = "5")
//    })
    public Page<UsuarioResumoDTO> listarPorOrgao(@PathVariable String orgao, @RequestParam(value = "todos", required = false) Boolean todos,
                                                 @PageableDefault(sort = "nome",  direction = Sort.Direction.DESC, page = 0, size = 10)
                                                 Pageable paginacao ) {
        return usuarioService.listarPorOrgao(orgao, todos, paginacao);
    }

    @GetMapping("unidade/{idUnidade}")
    public Page<UsuarioDTO> listarPorUnidae(@PathVariable Integer idUnidade, @PageableDefault(sort = "nome", direction = Sort.Direction.DESC, page = 0, size = 999) Pageable paginacao) {
        return usuarioService.listarPorUnidade(idUnidade, paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> consultar(@PathVariable Long id) {
        return usuarioService.consultar(id);
        }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UsuarioDTO> salvar(@RequestBody UsuarioDTO usuario) {
        return usuarioService.salvar(usuario);
    }

    @PutMapping("/{id}/mudarStatus")
    public ResponseEntity<UsuarioDTO> mudarStatus(@PathVariable Long id) {
        return usuarioService.mudarStatus(id);
    }

    @PutMapping("/mudarStatusEmLote")
    public void mudarStatusEmLote(@RequestBody @Valid List<Integer> usuarios) {
       // usuarioService.mudarStatusEmLote(usuarios);
    }

    @GetMapping("/usuarioSei/{orgao}")
    public List<RetornoConsultarUsuarioDTO> listarUsuariosSeiPorOrgao(@PathVariable String orgao) {
        return usuarioService.listarUsuariosSip(orgao);
    }

    @GetMapping("/filtrar")
    public Page<UsuarioDTO> listarUsuarioPorFiltro (
            UsuarioForms forms,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "5") Integer size
    ) {
        return this.usuarioService.listarUsuarioPorFiltro(forms, PageRequest.of(page, size, Sort.by("id")));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> atualizarUsuario(
            @RequestBody UsuarioDTO usuarioDTO,
            @PathVariable(name = "id") Integer id
    ) {
        return this.usuarioService.update(usuarioDTO, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletarUsuarioPorId (@PathVariable(name = "id") Integer id) {
       // return this.usuarioService.deletarUsuario(id);
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/usuario/{id}")
    public Usuario buscarPorId(@PathVariable(name = "id") Long id) {
        return this.usuarioRepository.findById(id).orElseThrow(() -> new NotFoundException(""));
    }

}
