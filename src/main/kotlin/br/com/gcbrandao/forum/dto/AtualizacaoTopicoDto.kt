package br.com.gcbrandao.forum.dto

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class AtualizacaoTopicoDto(

    @field:NotNull
    @field:NotEmpty
    val id: Long,

    @field:NotNull
    @field:NotEmpty
    val titulo: String,

    @field:NotNull
    @field:NotEmpty
    val mensagem: String
    )
