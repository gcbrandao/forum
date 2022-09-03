package br.com.gcbrandao.forum.mapper

import br.com.gcbrandao.forum.dto.NovoTopicoDto
import br.com.gcbrandao.forum.model.Topico
import br.com.gcbrandao.forum.service.CursoService
import br.com.gcbrandao.forum.service.UsuarioService
import org.springframework.stereotype.Component

@Component
class NovoTopicoDtoMapper(
    private val cursoService: CursoService,
    private val autorService: UsuarioService,
) : Mapper<NovoTopicoDto, Topico> {

    override fun map(t: NovoTopicoDto): Topico {
        return Topico(
            titulo = t.titulo,
            mensagem = t.mensagem,
            curso = cursoService.buscarPorId(t.idCurso),
            autor = autorService.buscarPorId(t.idAutor)
        )

    }
}