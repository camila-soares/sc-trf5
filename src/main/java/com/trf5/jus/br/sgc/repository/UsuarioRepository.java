package com.trf5.jus.br.sgc.repository;

import com.trf5.jus.br.sgc.domain.entity.Usuario;

import jakarta.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;



@Repository
@Transactional
public interface UsuarioRepository extends  JpaRepository<Usuario, Long>, JpaSpecificationExecutor<Usuario> {

    @Modifying
    @Query(value = "UPDATE usuario SET senha = ?1 WHERE id = ?2", nativeQuery = true)
    void atualizarSenha(String senha, Long id);

    public List<Usuario> findByLoginIgnoreCaseAndAtivoIsTrue(String nomeUsuario);
    public Usuario findByLoginIgnoreCaseAndOrgaoDescricaoOrgaoAndAtivoIsTrue(String nomeUsuario, String descricaoOrgao);
    public List<Usuario> findByNomeIgnoreCaseAndOrgaoCodigoOrgao(String nomeUsuario, Integer codigoOrgao);
    public Page<Usuario> findByOrgaoDescricaoOrgaoEqualsAndAtivo(String orgao, boolean ativo, Pageable paginacao);
    public Page<Usuario> findByOrgaoDescricaoOrgaoEquals(String orgao, Pageable paginacao);

    Page<Usuario> findByOrgaoIdIn(List<Integer> idsOrgaos, Pageable paginacao);
    Optional<Usuario> findByIdAndAtivoIsTrue(Integer idUsuario);

    Page<Usuario> findByOrgaoIdEquals(String idOrgao, boolean p, Pageable paginacao);
}
