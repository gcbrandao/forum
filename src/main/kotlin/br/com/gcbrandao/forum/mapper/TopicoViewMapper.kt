package br.com.gcbrandao.forum.mapper

import br.com.gcbrandao.forum.dto.TopicoView
import br.com.gcbrandao.forum.model.Topico
import org.springframework.stereotype.Component

@Component
class TopicoViewMapper: Mapper<Topico, TopicoView> {

    override fun map(t: Topico): TopicoView {
        return TopicoView(
            id = t.id,
            titulo = t.titulo,
            mensagem = t.mensagem,
            dataCriacao = t.dataCriacao,
            status = t.status.toString()
        )
    }
}