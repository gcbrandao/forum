package br.com.gcbrandao.forum.service

import br.com.gcbrandao.forum.exception.NotFoundException
import br.com.gcbrandao.forum.model.Usuario
import br.com.gcbrandao.forum.repository.UsuarioRepository

import org.springframework.stereotype.Service


@Service
class UsuarioService(private val usuarioRepository: UsuarioRepository) {

    fun buscarPorId(id: Long): Usuario {
        return usuarioRepository.findById(id).orElseThrow{
            NotFoundException("Usuario não Encontrado")
        }
    }
}
