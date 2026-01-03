package com.trf5.jus.br.sgc.service.impl;


import com.trf5.jus.br.sgc.domain.dto.RetornoConsultarUsuarioDTO;
import com.trf5.jus.br.sgc.domain.dto.UnidadeTecnicaDTO;
import com.trf5.jus.br.sgc.domain.dto.UsuarioResumoDTO;
import com.trf5.jus.br.sgc.domain.dto.login.UsuarioDTO;
import com.trf5.jus.br.sgc.domain.dto.sei.RetornoConsultarUsuario;
import com.trf5.jus.br.sgc.domain.dto.sei.Unidade;
import com.trf5.jus.br.sgc.domain.entity.OrgaoUsuario;
import com.trf5.jus.br.sgc.domain.entity.Usuario;
import com.trf5.jus.br.sgc.domain.enums.OrgaoSarhEnum;
import com.trf5.jus.br.sgc.domain.enums.Perfil;
import com.trf5.jus.br.sgc.forms.UsuarioForms;
import com.trf5.jus.br.sgc.repository.*;
import com.trf5.jus.br.sgc.util.AuthUtils;
import com.trf5.jus.br.sgc.util.Util;
import com.trf5.jus.br.sgc.webservice.ConsultaUsuarioDws;
import jakarta.transaction.Transactional;
import jakarta.validation.GroupDefinitionException;
import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UsuarioService {

    private final UnidadeRepository unidadesRepository;
    private final UsuarioRepository usuarioRepository;
    private final OrgaoUsuarioRepository orgaoUsuarioRepository;

    private final List<String> PERFIS_ADMIN = Arrays.asList(Perfil.ADMIN.getPerfil(),
            Perfil.SUP.getPerfil());

    public void existe(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new NotFoundException(Util.getPropMensagem().getString("usuario.erro.naoencontrado"));
        }
    }

    public ResponseEntity<Usuario> salve(Usuario usuario) {
      //  return ResponseEntity.ok(usuarioRepository.atualizarSenha(usuario.getSenha(), usuario.getId()));
        usuarioRepository.atualizarSenha(usuario.getSenha(), usuario.getId());
        usuarioRepository.save(usuario);
        return ResponseEntity.ok(usuarioRepository.findById(usuario.getId()).get());
    }
    public ResponseEntity<UsuarioDTO> salvar(UsuarioDTO usuarioDTO) {

        this.validarExisteUsuarioAtivo(usuarioDTO);

        Usuario usuario = new Usuario();

        usuario.setNome(usuarioDTO.getNome());
        usuario.setLogin(usuarioDTO.getLogin());
      //  usuario.setSenha(usuarioDTO.getSenha());
        usuario.setAtivo(true);

        OrgaoUsuario orgaoUsuario = new OrgaoUsuario();
        orgaoUsuario.setDescricaoOrgao(usuarioDTO.getSecao());

        orgaoUsuario.setCodigoOrgao(OrgaoSarhEnum.obterIdOrgao(usuarioDTO.getSecao()));


        if (!usuarioDTO.getUnidades().isEmpty()) {
            List<UnidadeTecnicaDTO> unidades = usuarioDTO.getUnidades();
            for (UnidadeTecnicaDTO unidadeResumoDTO : unidades) {
                Optional<Unidade> unidadelist = unidadesRepository.findById(Long.valueOf(unidadeResumoDTO.getIdUnidadeGestora()));
                orgaoUsuario.getUnidades().add(unidadelist.get());
            }
        } else if (possuiQualquerPerfil(usuarioDTO.getRoles(), PERFIS_ADMIN)) {

            orgaoUsuario.setUnidades(unidadesRepository
                    .findByAtivaIsTrueAndUnidadeTecnicaIsTrueAndSiglaUnidadeGestoraIgnoreCase(usuarioDTO.getSecao()));
        }

        usuario.setOrgao(orgaoUsuario);

        usuario.adicionarPermissoes(usuarioDTO.getRoles());
        usuario.setRoles(usuarioDTO.getRoles());

        usuarioRepository.save(usuario);
        return ResponseEntity.ok(new UsuarioDTO(usuario));
    }


//    public ResponseEntity<HttpStatus> deletarUsuario(Integer idUsuario) {
//        Usuario usuario = this.usuarioRepository.findById(idUsuario)
//                .orElseThrow(() -> new NotFoundException("Usuário não foi encontrado"));
//
//        List<Unidade> unidades = usuario.getOrgao().getUnidades();
//
//        unidades.forEach(obj -> {
//            boolean existUnidadeDemandaAtiva = this.demandaRepository
//                    .existsByAtivoIsTrueAndUnidadeIdEquals(obj.getId());
//            if (existUnidadeDemandaAtiva) {
//                throw new NotFoundException(Util.getPropMensagem().getString("usuario.erro.deletar"));
//            }
//        });
//
//        List<ConfiguracaoUsuario> listaConfig = this.configRepository.findByUsuarioId(idUsuario);
//
//        listaConfig.forEach(config -> {
//            this.configRepository.delete(config);
//        });
//
//        this.usuarioRepository.delete(usuario);
//
//        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
//    }

    public ResponseEntity<UsuarioDTO> consultar(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            if (usuario.get().getOrgao().getUnidades().isEmpty()) {
                usuario.get().getOrgao().setUnidades(unidadesRepository.findByAtivaAndUnidadeTecnicaAndSiglaUnidadeGestora(true, true, usuario.get().getOrgao().getDescricaoOrgao()));
            }
            return ResponseEntity.ok(new UsuarioDTO(usuario.get()));
        }

        return ResponseEntity.notFound().build();
    }

    private List<Usuario> listarPorNomeOrgao(String nomeUsuario, Integer codigoOrgao) {
        return usuarioRepository.findByNomeIgnoreCaseAndOrgaoCodigoOrgao(nomeUsuario, codigoOrgao);
    }

    private Usuario getByLoginUsers(String loginUsuario) {
        return usuarioRepository.findByLoginIgnoreCaseAndAtivoIsTrue(loginUsuario).get(0);
    }

    public Usuario getByLoginOrgao(String loginUsuario, String orgao) {
        return usuarioRepository.findByLoginIgnoreCaseAndOrgaoDescricaoOrgaoAndAtivoIsTrue(loginUsuario, orgao);
    }


    public Page<UsuarioResumoDTO> listar(Boolean idOrgao, Pageable paginacao) {
        Page<Usuario> usuario = usuarioRepository.findByOrgaoIdEquals(String.valueOf(idOrgao), true, paginacao);
        return UsuarioResumoDTO.converter(usuario);
    }

        public Page<UsuarioDTO> listarPorUnidade(Integer idUnidade, Pageable paginacao) {
        List<OrgaoUsuario> orgaos = orgaoUsuarioRepository.findAll();
        List<OrgaoUsuario> orgaosComEssaUnidade = new ArrayList<>();

        orgaos.stream().forEach(o -> {
            o.getUnidades().forEach(unidade -> {
                if (unidade.getId().compareTo(Long.valueOf(idUnidade)) == 0) {
                    orgaosComEssaUnidade.add(o);
                }
            });
        });

        List<Integer> orgaosIds = orgaosComEssaUnidade.stream().map(o -> o.getId()).collect(Collectors.toList());

        Page<Usuario> usuario = usuarioRepository.findByOrgaoIdIn(orgaosIds, paginacao);

        return UsuarioDTO.converter(usuario);
    }
//
//    public Page<UsuarioResumoDTO> listar(Boolean todos, Pageable paginacao) {
//        if (todos == null || todos) {
//            Page<Usuario> usuarios = usuarioRepository.findAll(paginacao);
//            return UsuarioResumoDTO.converter(usuarios);
//        } else {
//            Page<Usuario> usuarios = usuarioRepository.findByAtivo(true, paginacao);
//            return UsuarioResumoDTO.converter(usuarios);
//        }
//
//    }
//
    public Page<UsuarioResumoDTO> listarPorOrgao(String orgao, Boolean todos, Pageable paginacao) {
        if (todos == null || todos) {
            Page<Usuario> usuarios = usuarioRepository.findByOrgaoDescricaoOrgaoEquals(orgao, paginacao);
            return UsuarioResumoDTO.converter(usuarios);
        } else {
            Page<Usuario> usuarios = usuarioRepository.findByOrgaoDescricaoOrgaoEqualsAndAtivo(orgao, true, paginacao);
            return UsuarioResumoDTO.converter(usuarios);
        }

    }

    public Usuario consultarPorLogin(String nomeUsuario) {
        return getByLoginUsers(nomeUsuario);
    }

    public ResponseEntity<UsuarioDTO> mudarStatus(Long id) {
        this.existe(id);

        Optional<Usuario> usuario = usuarioRepository.findById(id);

        if (!usuario.get().getAtivo()) {
            this.validarNomeAtivoOrgao(usuario.get().getNome(), usuario.get().getOrgao().getCodigoOrgao());
        }

        usuario.get().setAtivo(!usuario.get().getAtivo());
        usuarioRepository.save(usuario.get());

        return ResponseEntity.ok(new UsuarioDTO(usuario.get()));

    }

//    public void mudarStatusEmLote(List<Integer> usuarios) {
//
//        StringBuilder erros = new StringBuilder();
//        usuarios.forEach(usu -> {
//            try {
//                this.existe(usu);
//
//                Optional<Usuario> usuario = usuarioRepository.findById(usu);
//
//                if (!usuario.get().getAtivo()) {
//                    this.validarNomeAtivoOrgao(usuario.get().getNome(), usuario.get().getOrgao().getCodigoOrgao());
//                }
//
//                usuario.get().setAtivo(!usuario.get().getAtivo());
//                usuarioRepository.save(usuario.get());
//            } catch (Exception e) {
//                if (erros.length() > 0) {
//                    erros.append(", " + usu);
//                } else {
//                    erros.append(usu);
//                }
//
//            }
//        });
//
//        if (erros.length() > 0) {
//            throw new NegocioException(Util.getPropMensagem().getString("usuario.erro.inativar.lote") + erros + ".");
//        }
//
//    }

    public void validarExisteUsuarioAtivo(UsuarioDTO usuarioDTO) {
        Usuario usuario = this.getByLoginOrgao(usuarioDTO.getLogin(), usuarioDTO.getSecao());
        if (usuario != null && usuario.getAtivo()) {
            throw new RuntimeException(Util.getPropMensagem().getString("usuario.erro.cadastro.ativo"));
        }
    }

    public void validarNomeAtivoOrgao(String nomeUsuario, Integer codigoOrgao) {
        List<Usuario> lista = (List<Usuario>) this.listarPorNomeOrgao(nomeUsuario, codigoOrgao);
        if (!lista.isEmpty()) {
            lista.forEach(n -> {
                if (Util.removerAcentos(n.getNome()).equalsIgnoreCase(Util.removerAcentos(nomeUsuario))
                        && n.getAtivo()) {
                    throw new RuntimeException(Util.getPropMensagem().getString("usuario.erro.cadastro.ativo"));
                }
            });
        }
    }

    public List<RetornoConsultarUsuarioDTO> listarUsuariosSip(String orgao) {

        ConsultaUsuarioDws consultarUsuarioDws = new ConsultarUsuarioDwImp();

        try {
            List<RetornoConsultarUsuario> usuariosSip = consultarUsuarioDws.consultarUsuarios(orgao);

            if (usuariosSip.isEmpty()) {
                throw new GroupDefinitionException(Util.getPropMensagem().getString("usuario.erro.nao.encontrado.sei"));
            }


            return RetornoConsultarUsuarioDTO.converter(usuariosSip);

        } catch (Exception e) {
            e.printStackTrace();
            throw new GroupDefinitionException(Util.getPropMensagem().getString("webservice.erro"));
        }
    }

    public Page<UsuarioDTO> listarUsuarioPorFiltro(UsuarioForms forms, Pageable pageable) {
        Page<Usuario> usuarios = this.usuarioRepository.findAll(forms.toSpec(), pageable);
        return UsuarioDTO.converter(usuarios);
    }

    private boolean possuiQualquerPerfil(String[] roles, List<String> rolesProcuradas) {
        return Arrays.stream(roles)
                .anyMatch(role -> rolesProcuradas.stream().anyMatch(roleProcurada -> roleProcurada.equals(role)));
    }

    private void adicionaTodasUnidadesTecnicas(Usuario usuario) {
        usuario.getOrgao()
                .setUnidades(unidadesRepository.findByAtivaIsTrueAndUnidadeTecnicaIsTrueAndSiglaUnidadeGestoraIgnoreCase(
                        usuario.getOrgao().getDescricaoOrgao()));
    }

    public Usuario getUsuarioLogado() {
        return getByLoginOrgao(AuthUtils.usuarioLogado().getUsername(), AuthUtils.getOrgao());
    }


    UsuarioDTO buscarPorLoginUnidadeGestora(String login, String unidadeGestora) {
        Usuario retorno = this.getByLoginOrgao(login, unidadeGestora);
        if (retorno != null) {
            java.util.function.Predicate<Unidade> predicateAtiva = unidade -> unidade.getAtiva();
            java.util.function.Predicate<Unidade> predicateUnidadeGestora = unidade -> unidade.getSiglaUnidadeGestora()
                    .equals(unidadeGestora);

            List<UnidadeTecnicaDTO> unidades = retorno.getOrgao().getUnidades().stream()
                    .filter(predicateAtiva.and(predicateUnidadeGestora)).map(UnidadeTecnicaDTO::new)
                    .collect(Collectors.toList());

            UsuarioDTO dto = new UsuarioDTO();
            dto.setLogin(retorno.getLogin());
            dto.setNome(retorno.getNome());
            dto.setDescricaoOrgao(unidadeGestora);
            dto.setUnidades(unidades);
            return dto;
        }
        return null;
    }


    public ResponseEntity<UsuarioDTO> update(@Valid UsuarioDTO usuarioDTO, int idUsuario) {
        Optional<Usuario> usuario = Optional.ofNullable(this.usuarioRepository.findByIdAndAtivoIsTrue(idUsuario)
                .orElseThrow(() -> new NotFoundException("Usuário ativo não encontrado")));

        OrgaoUsuario orgaoUsuario = usuario.get().getOrgao();
        usuario.get().getOrgao().getUnidades().clear();

        if (possuiQualquerPerfil(usuarioDTO.getRoles(), PERFIS_ADMIN)) {
            adicionaTodasUnidadesTecnicas(usuario.get());
        } else if (!usuarioDTO.getUnidades().isEmpty()) {
            List<UnidadeTecnicaDTO> unidades = usuarioDTO.getUnidades();
            for (UnidadeTecnicaDTO unidadeResumoDTO : unidades) {
                Optional<Unidade> unidade = Optional.ofNullable(unidadesRepository.findById(Long.valueOf(unidadeResumoDTO.getId()))
                        .orElseThrow(() ->
                                new NotFoundException("unidade na encontrrado")));
                orgaoUsuario.getUnidades().add(unidade.get());
            }
        } else {
            usuario.get().getOrgao().getUnidades().clear();
        }
        return ResponseEntity.ok(usuarioDTO);

    }
}
//         usuario.get().setOrgao(orgaoUsuario);
//
//        List<PermissaoUsuario> permissoes = permissaoUsarioRepository.findById(usuario.get().getId());
//
//        usuario.get().getPermissoes().clear();
//        usuario.get().adicionarPermissoes(usuarioDTO.getRoles());
//
//        permissaoUsarioRepository.deleteAll(permissoes);
//        usuarioRepository.save(usuario);
//
//        return ResponseEntity.ok(new UsuarioDTO(usuario));
//    }
//}