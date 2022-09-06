package br.com.gcbrandao.forum.service

import br.com.gcbrandao.forum.dto.AtualizacaoTopicoDto
import br.com.gcbrandao.forum.dto.NovoTopicoDto
import br.com.gcbrandao.forum.dto.TopicoView
import br.com.gcbrandao.forum.exception.NotFoundException
import br.com.gcbrandao.forum.mapper.NovoTopicoDtoMapper
import br.com.gcbrandao.forum.mapper.TopicoViewMapper
import br.com.gcbrandao.forum.model.Topico
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class TopicoService(
    private var topicos: List<Topico> = ArrayList(),
    private val novoTopicoDtoMapper: NovoTopicoDtoMapper,
    private val topicoViewMapper: TopicoViewMapper
) {
    private val notFoundMessage: String = "Item nao encontrado!!"
    fun listar(): List<TopicoView> {
        return topicos.stream().map { t ->
            topicoViewMapper.map(t)
        }.collect(Collectors.toList())
    }

    fun buscarPorId(id: Long): TopicoView {
        val topico = topicos.stream().filter { t ->
            t.id == id
        }.findFirst().orElseThrow{NotFoundException(notFoundMessage)}

        return topicoViewMapper.map(topico)
    }

    fun cadastrar(dto: NovoTopicoDto): TopicoView  {

        val topico = novoTopicoDtoMapper.map(dto)
        topico.id = topicos.size.toLong() + 1

        topicos = topicos.plus(topico)

        return topicoViewMapper.map(topico)
    }

    fun atualizar(dto: AtualizacaoTopicoDto) {
        topicos.plus(dto)
    }

    fun apagar(id: Long) {
        val topic = topicos.filter { t ->
            t.id == id
        }.stream().findFirst().orElseThrow{ NotFoundException(notFoundMessage)}
        val minusTopic = topicos.minus(topic)
    }
}