package br.com.gcbrandao.forum.dto

import com.sun.jdi.connect.Connector.StringArgument

data class NovoTopicoDto (

    val titulo: String,
    val mensagem: String,
    val idCurso: Long,
    val idAutor: Long

)
