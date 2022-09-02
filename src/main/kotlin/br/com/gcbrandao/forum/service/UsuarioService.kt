package br.com.gcbrandao.forum.service

import br.com.gcbrandao.forum.model.Usuario
import org.springframework.stereotype.Service
import java.util.*

@Service
class UsuarioService(var usuarios: List<Usuario>) {

    init {
        val usuario = Usuario(
            id = 1,
            nome = "Maria",
            email = "maria@gmal.com"
        )
        usuarios = Arrays.asList(usuario)
    }

    fun buscarPorId(id: Long): Usuario {
        return usuarios.stream().filter({ c ->
            c.id == id
        }).findFirst().get()
    }
}
