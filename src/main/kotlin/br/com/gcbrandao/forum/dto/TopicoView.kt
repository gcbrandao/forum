package br.com.gcbrandao.forum.dto

import java.time.LocalDateTime

data class TopicoView(
    val id: Long?,
    val titulo: String,
    val mensagem: String,
    val status: String,
    val dataCriacao: LocalDateTime,
    val dataAlteracao: LocalDateTime?
)
