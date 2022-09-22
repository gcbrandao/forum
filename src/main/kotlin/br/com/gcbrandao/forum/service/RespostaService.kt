package br.com.gcbrandao.forum.service

import br.com.gcbrandao.forum.model.Resposta
import br.com.gcbrandao.forum.repository.RespostaRepository
import org.springframework.stereotype.Service

@Service
class RespostaService
    (
    private val respostaRepository: RespostaRepository,
    private val emailService: EmailService
) {


    fun salvar(resposta: Resposta) : Resposta {
        val resp = respostaRepository.save(resposta)
        val autor = resp.autor.email
        emailService.notificar(autor)

        return resp

    }

}