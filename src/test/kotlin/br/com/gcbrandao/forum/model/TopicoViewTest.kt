package br.com.gcbrandao.forum.model

import br.com.gcbrandao.forum.dto.TopicoView
import java.time.LocalDateTime

object TopicoViewTest {
    fun build() = TopicoView(
        id = 1,
        titulo = "Kotlin Basico",
        mensagem = "Aprendendo kotlin basico",
        status = StatusTopico.NAO_RESPONDIDO.toString(),
        dataCriacao = LocalDateTime.now(),
        dataAlteracao = LocalDateTime.now()
    )
}