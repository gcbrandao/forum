package br.com.gcbrandao.forum.repository;

import br.com.gcbrandao.forum.model.Curso
import org.springframework.data.jpa.repository.JpaRepository

interface CursoRepository : JpaRepository<Curso, Long> {
}