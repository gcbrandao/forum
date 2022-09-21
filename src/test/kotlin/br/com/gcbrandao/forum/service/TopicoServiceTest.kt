package br.com.gcbrandao.forum.service

import br.com.gcbrandao.forum.exception.NotFoundException
import br.com.gcbrandao.forum.mapper.NovoTopicoDtoMapper
import br.com.gcbrandao.forum.mapper.TopicoViewMapper
import br.com.gcbrandao.forum.model.TopicoTest
import br.com.gcbrandao.forum.model.TopicoViewTest
import br.com.gcbrandao.forum.repository.TopicoRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import java.util.*

class TopicoServiceTest {

    val topicos = PageImpl(listOf(TopicoTest.build()))

    val paginacao: Pageable = mockk()

    val topicoRepository: TopicoRepository = mockk {
        every { findByCursoNome(any(), any()) } returns topicos
        every { findAll(paginacao) } returns topicos
    }


    val topicoViewMapper: TopicoViewMapper = mockk() {
        every { map(any()) } returns TopicoViewTest.build()
    }
    val novoTopicoDtoMapper: NovoTopicoDtoMapper = mockk()


    val topicoService = TopicoService(topicoRepository, novoTopicoDtoMapper, topicoViewMapper)


    @Test
    fun `deve listar topicos a partir do nome do curso`() {


        topicoService.listar("kotlin avancado", paginacao)
        verify(exactly = 1) { topicoRepository.findByCursoNome(any(), any()) }
        verify(exactly = 1) { topicoViewMapper.map(any()) }
        verify(exactly = 0) { topicoRepository.findAll(paginacao) }

    }

    @Test
    fun `deve listar todos os topicos quando o nome do curso for nulo`() {

        topicoService.listar(null, paginacao)
        verify(exactly = 0) { topicoRepository.findByCursoNome(any(), any()) }
        verify(exactly = 1) { topicoViewMapper.map(any()) }
        verify(exactly = 1) { topicoRepository.findAll(paginacao) }
    }

    @Test
    fun `deve listar not foun exception quando topico nao for encontrado`() {

        every { topicoRepository.findById(any()) } returns Optional.empty()

        val atual =  assertThrows<NotFoundException> {
            topicoService.buscarPorId(1)
        }
        assertThat(atual.message).isEqualTo("Item nao encontrado!!")
    }

}