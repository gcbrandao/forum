package br.com.gcbrandao.forum.dto

import com.sun.jdi.connect.Connector.StringArgument
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class NovoTopicoDto (

    @field:NotEmpty
    @field:Size(min = 5, max = 40)
    val titulo: String,

    @field:NotEmpty
    val mensagem: String,

    @field:NotNull
    val idCurso: Long,

    @field:NotNull
    val idAutor: Long

)
