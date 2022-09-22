package br.com.gcbrandao.forum.controller

import br.com.gcbrandao.forum.dto.NovoTopicoDto
import br.com.gcbrandao.forum.dto.TopicoView
import br.com.gcbrandao.forum.model.Resposta
import br.com.gcbrandao.forum.service.RespostaService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/respostas")
class RespostaController
    (
    private val respostaService: RespostaService
) {

    @PostMapping
    fun salvar(
        @RequestBody @Valid resposta: Resposta,
        uriComponentsBuilder: UriComponentsBuilder
    ): ResponseEntity<Resposta> {
        val respostaRet = respostaService.salvar(resposta)

        val toUri = uriComponentsBuilder.path("/resposta/${respostaRet.id}")
            .build()
            .toUri()

        return ResponseEntity.created(toUri).body(respostaRet)
    }


}