package br.com.gcbrandao.forum.repository;

import br.com.gcbrandao.forum.model.Resposta
import org.springframework.data.jpa.repository.JpaRepository

interface RespostaRepository : JpaRepository<Resposta, Long> {

}