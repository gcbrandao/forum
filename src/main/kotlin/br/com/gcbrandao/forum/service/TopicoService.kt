package br.com.gcbrandao.forum.service

import br.com.gcbrandao.forum.dto.AtualizacaoTopicoDto
import br.com.gcbrandao.forum.dto.NovoTopicoDto
import br.com.gcbrandao.forum.dto.TopicoPorCategoria
import br.com.gcbrandao.forum.dto.TopicoView
import br.com.gcbrandao.forum.exception.NotFoundException
import br.com.gcbrandao.forum.mapper.NovoTopicoDtoMapper
import br.com.gcbrandao.forum.mapper.TopicoViewMapper
import br.com.gcbrandao.forum.model.Topico
import br.com.gcbrandao.forum.repository.TopicoRepository
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.stream.Collectors

@Service
class TopicoService(
    private val topicoRepository: TopicoRepository,
    private val novoTopicoDtoMapper: NovoTopicoDtoMapper,
    private val topicoViewMapper: TopicoViewMapper
) {
    private val notFoundMessage: String = "Item nao encontrado!!"

    @Cacheable(cacheNames = ["topicos"], key = "#root.method.name")
    fun listar(
        nomeCurso: String?,
        paginacao: Pageable
    ): Page<TopicoView> {
        val topicos = if (nomeCurso == null) {
            topicoRepository.findAll(paginacao)
        } else {
            topicoRepository.findByCursoNome(nomeCurso, paginacao)
        }
        return topicos.map { t ->
            topicoViewMapper.map(t)
        }
    }

    fun buscarPorId(id: Long): TopicoView {
        val topico = topicoRepository
            .findById(id).orElseThrow { NotFoundException(notFoundMessage) }

        return topicoViewMapper.map(topico)
    }

    @CacheEvict(cacheNames = ["topicos"], allEntries = true)
    fun cadastrar(dto: NovoTopicoDto): TopicoView {

        val topico = topicoRepository.save(novoTopicoDtoMapper.map(dto))
        return topicoViewMapper.map(topico)
    }

    @CacheEvict(cacheNames = ["topicos"], allEntries = true)
    fun atualizar(dto: AtualizacaoTopicoDto) {
        var topico = topicoRepository.findById(dto.id).orElseThrow {
            NotFoundException("Topico Não encontrado")
        }

        topico.mensagem = dto.mensagem
        topico.titulo = dto.titulo
        topico.dataAlteracao = LocalDateTime.now()

        topicoRepository.saveAndFlush(topico)
    }

    @CacheEvict(cacheNames = ["topicos"], allEntries = true)
    fun apagar(id: Long) {

        topicoRepository.deleteById(id)
    }

    fun relatorio(): List<TopicoPorCategoria> {
        return topicoRepository.relatorio()
    }
}