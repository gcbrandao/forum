package br.com.gcbrandao.forum.repository;

import br.com.gcbrandao.forum.dto.TopicoPorCategoria
import br.com.gcbrandao.forum.model.Topico
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface TopicoRepository : JpaRepository<Topico, Long> {

    fun findByCursoNome(nomeCurso: String, pagiancao: Pageable): Page<Topico>

    @Query("select new br.com.gcbrandao.forum.dto.TopicoPorCategoria(curso.categoria, count(t)) " +
            "FROM Topico t JOIN t.curso curso GROUP BY   curso.categoria ")
    fun relatorio(): List<TopicoPorCategoria>

}