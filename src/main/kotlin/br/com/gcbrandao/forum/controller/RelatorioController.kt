package br.com.gcbrandao.forum.controller

import br.com.gcbrandao.forum.dto.TopicoPorCategoria
import br.com.gcbrandao.forum.service.TopicoService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/relatorios")
class RelatorioController(
    private val topicoService: TopicoService
) {


    @GetMapping
    fun relatorio(model: Model): String {
        model.addAttribute("topicosPorCategorias", topicoService.relatorio())
        return "relatorio"
    }
}