package br.com.gcbrandao.forum.service

import br.com.gcbrandao.forum.model.Curso
import br.com.gcbrandao.forum.model.Topico
import br.com.gcbrandao.forum.model.Usuario
import org.springframework.stereotype.Service
import java.util.*

@Service
class TopicoService(private var topicos: List<Topico>) {

    init {
        val topico1 = Topico(
            id = 1,
            titulo = "Kootlin",
            mensagem = "TEste de mensagem",
            curso = Curso(
                id = 1,
                nome = "Curso Kotlin",
                categoria = "Programacao"
            ),
            autor = Usuario(
                id = 1,
                nome = "Maria",
                email = "maria@gmail.com"
            ),
        )
        val topico2 = Topico(
            id = 2,
            titulo = "Java",
            mensagem = "Como funciona",
            curso = Curso(
                id = 2,
                nome = "Curso java",
                categoria = "Programacao"
            ),
            autor = Usuario(
                id = 2,
                nome = "clara",
                email = "clara@gmail.com"
            ),
        )
        val topico3 = Topico(
            id = 3,
            titulo = "SPRING BOOT   ",
            mensagem = "Framework",
            curso = Curso(
                id = 3,
                nome = "Curso SpringBoot",
                categoria = "Programacao"
            ),
            autor = Usuario(
                id = 3,
                nome = "dylan",
                email = "duylan@gmail.com"
            ),
        )

        topicos = Arrays.asList(topico1, topico2, topico3)

    }

    fun listar(): List<Topico> {
        return topicos
    }

    fun buscarPorId(id: Long): Topico {
        return topicos.stream().filter({
            t -> t.id == id
        }).findFirst().get()
    }
}