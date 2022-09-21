package br.com.gcbrandao.forum.integration

import br.com.gcbrandao.forum.dto.TopicoPorCategoria
import br.com.gcbrandao.forum.model.TopicoTest
import br.com.gcbrandao.forum.repository.TopicoRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.domain.PageRequest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers


@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TopicoRepositoryTest {

    @Autowired
    private lateinit var topicoRepository: TopicoRepository


    private val topico = TopicoTest.build()

    companion object {
        @Container
        private val mySQLContainer = MySQLContainer<Nothing>("mysql:8.0.28")
            .apply {
                withDatabaseName("testdb")
                withUsername("userTest")
                withPassword("passwd")

            }

        @JvmStatic
        @DynamicPropertySource
        fun proprerties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl)
            registry.add("spring.datasource.password", mySQLContainer::getPassword)
            registry.add("spring.datasource.username", mySQLContainer::getUsername)
        }
    }

    @Test
    fun `deve gerar um relatorio` () {
        topicoRepository.save(topico)
        val relatorio = topicoRepository.relatorio()

        assertThat(relatorio).isNotNull
        assertThat(relatorio.first()).isExactlyInstanceOf(TopicoPorCategoria::class.java)
    }

    @Test
    fun `deve listar topicos pelo nome do curso`() {
        topicoRepository.save(topico)
        val topicoPage = topicoRepository.findByCursoNome(topico.curso.nome, PageRequest.of(0, 5))

        assertThat(topicoPage).isNotNull
    }


}