package br.com.gcbrandao.forum.service

import br.com.gcbrandao.forum.exception.NotFoundException
import br.com.gcbrandao.forum.model.Curso
import br.com.gcbrandao.forum.repository.CursoRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class CursoService(private val cursoRepository: CursoRepository) {


    fun buscarPorId(id: Long): Curso {
        return cursoRepository.findById(id).orElseThrow {
            NotFoundException("Curso não encontrado")
        }
    }

}
