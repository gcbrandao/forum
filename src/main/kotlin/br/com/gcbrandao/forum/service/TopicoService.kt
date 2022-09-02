package br.com.gcbrandao.forum.service

import br.com.gcbrandao.forum.dto.NovoTopicoDto
import br.com.gcbrandao.forum.dto.TopicoView
import br.com.gcbrandao.forum.model.Curso
import br.com.gcbrandao.forum.model.Topico
import br.com.gcbrandao.forum.model.Usuario
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
class TopicoService(
    private var topicos: List<Topico> = ArrayList(),
    private val cursoService: CursoService,
    private val autorService: UsuarioService
) {


/*    init {
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
*/
    fun listar(): List<TopicoView> {
        return topicos.stream().map { t -> TopicoView(
            id = t.id,
            titulo = t.titulo,
            mensagem = t.mensagem,
            status = t.status.toString(),
            dataCriacao = t.dataCriacao
        )}.collect(Collectors.toList())
    }

    fun buscarPorId(id: Long): TopicoView {
        val topico = topicos.stream().filter({ t ->
            t.id == id
        }).findFirst().get()

        return TopicoView(
            id = topico.id,
            titulo = topico.titulo,
            mensagem = topico.mensagem,
            dataCriacao = topico.dataCriacao,
            status = topico.status.toString()
        )
    }

    fun cadastrar(dto: NovoTopicoDto) {
       topicos =  topicos.plus(
            Topico(
                id = topicos.size.toLong(),
                titulo = dto.titulo,
                mensagem = dto.mensagem,
                curso = cursoService.buscarPorId(dto.idCurso),
                autor = autorService.buscarPorId(dto.idAutor)
            )
        )
        println(topicos.toString())
    }
}