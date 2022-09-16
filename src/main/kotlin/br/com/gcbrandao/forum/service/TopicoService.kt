package br.com.gcbrandao.forum.service

import br.com.gcbrandao.forum.dto.AtualizacaoTopicoDto
import br.com.gcbrandao.forum.dto.NovoTopicoDto
import br.com.gcbrandao.forum.dto.TopicoView
import br.com.gcbrandao.forum.exception.NotFoundException
import br.com.gcbrandao.forum.mapper.NovoTopicoDtoMapper
import br.com.gcbrandao.forum.mapper.TopicoViewMapper
import br.com.gcbrandao.forum.model.Topico
import br.com.gcbrandao.forum.repository.TopicoRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class TopicoService(
    private val topicoRepository: TopicoRepository,
    private val novoTopicoDtoMapper: NovoTopicoDtoMapper,
    private val topicoViewMapper: TopicoViewMapper
) {
    private val notFoundMessage: String = "Item nao encontrado!!"
    fun listar(): List<TopicoView> {
        return topicoRepository.findAll().stream().map { t ->
            topicoViewMapper.map(t)
        }.collect(Collectors.toList())
    }

    fun buscarPorId(id: Long): TopicoView {
        val topico = topicoRepository
            .findById(id).orElseThrow{NotFoundException(notFoundMessage)}

        return topicoViewMapper.map(topico)
    }

    fun cadastrar(dto: NovoTopicoDto): TopicoView  {

        val topico = topicoRepository.save(novoTopicoDtoMapper.map(dto))
        return topicoViewMapper.map(topico)
    }

    fun atualizar(dto: AtualizacaoTopicoDto) {
        var topico = topicoRepository.findById(dto.id).orElseThrow {
            NotFoundException("Topico Não encontrado")
        }

        topico.mensagem = dto.mensagem
        topico.titulo = dto.titulo

        topicoRepository.saveAndFlush(topico)
    }

    fun apagar(id: Long) {

        topicoRepository.deleteById(id)
    }
}