package br.com.gcbrandao.forum.repository;

import br.com.gcbrandao.forum.model.Usuario
import org.springframework.data.jpa.repository.JpaRepository

interface UsuarioRepository : JpaRepository<Usuario, Long> {
    fun findByEmail(username: String?): Usuario?
}