package br.com.messore.tech.pokedex.data.network.source

import br.com.messore.tech.pokedex.data.network.model.GraphQLRequest
import br.com.messore.tech.pokedex.data.network.model.PokemonResponse
import br.com.messore.tech.pokedex.data.network.service.PokemonService
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class PokemonRemoteDataSourceTest {
    private val service: PokemonService = mockk()

    private val dataSource = PokemonRemoteDataSource(service)

    @Test
    fun `should build query correctly`() = runTest {
        // arrange
        val page = 1
        val pageSize = 10
        val term = "bul"
        val expectedRequest = GraphQLRequest(
            query = """
                query (${'$'}term: String = "%%") {
                  pokemon: pokemon_v2_pokemon(offset: 10, where: {name: {_ilike: ${'$'}term}}, limit: 10, order_by: {}) {
                    id
                    name
                    types: pokemon_v2_pokemontypes(order_by: {slot: asc}) {
                      type: pokemon_v2_type {
                        id
                        name
                      }
                    }
                  }
                }
            """.trimIndent(),
            variables = mapOf(
                "term" to "%bul%"
            )
        )
        val requestSlot = slot<GraphQLRequest>()

        coEvery {
            service.post(capture(requestSlot))
        } returns PokemonResponse(data = PokemonResponse.Data(listOf()))

        // act
        dataSource.getPokemons(
            page = page,
            term = term,
            pageSize = pageSize,
            type = null,
            sort = null,
        )

        // assert
        assertEquals(expectedRequest, requestSlot.captured)
    }
}
