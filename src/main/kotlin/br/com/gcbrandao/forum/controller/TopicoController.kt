package br.com.gcbrandao.forum.controller

import br.com.gcbrandao.forum.dto.AtualizacaoTopicoDto
import br.com.gcbrandao.forum.dto.NovoTopicoDto
import br.com.gcbrandao.forum.dto.TopicoPorCategoria
import br.com.gcbrandao.forum.dto.TopicoView
import br.com.gcbrandao.forum.model.Curso
import br.com.gcbrandao.forum.model.Topico
import br.com.gcbrandao.forum.model.Usuario
import br.com.gcbrandao.forum.service.TopicoService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.security.SecurityScheme
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriBuilder
import org.springframework.web.util.UriComponentsBuilder
import java.util.*
import javax.validation.Valid
import kotlin.collections.List

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/topicos")
class TopicoController(private val service: TopicoService) {

    @GetMapping
    fun listar(
        @RequestParam(required = false) nomeCurso: String?,
        @PageableDefault(size = 5, sort = ["dataCriacao"], direction = Sort.Direction.DESC) paginacao: Pageable
    ): Page<TopicoView> {
        return service.listar(nomeCurso, paginacao)
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): TopicoView {
        return service.buscarPorId(id)
    }

    @PostMapping
    @Transactional
    fun cadastrar(
        @RequestBody @Valid dto: NovoTopicoDto,
        uriComponentsBuilder: UriComponentsBuilder
    ): ResponseEntity<TopicoView> {
        val topicoDtoRet = service.cadastrar(dto)

        val toUri = uriComponentsBuilder.path("/topicos/${topicoDtoRet.id}")
            .build()
            .toUri()

        return ResponseEntity.created(toUri).body(topicoDtoRet)
    }

    @PutMapping
    @Transactional
    fun atualizar(@RequestBody @Valid dto: AtualizacaoTopicoDto) {
        service.atualizar(dto)
    }

    @DeleteMapping
    @Transactional
    fun deletar(@PathVariable id: Long) {
        service.apagar(id)
    }

    @GetMapping("/relatorio")
    fun relatorio(): ResponseEntity<List<TopicoPorCategoria>> {
        print(service.relatorio())
        val relatorio = service.relatorio()

        return ResponseEntity.ok(relatorio)
    }
}