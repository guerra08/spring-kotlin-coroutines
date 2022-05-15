package com.example.springkotlincoroutines.integration

import com.example.springkotlincoroutines.domain.Product
import com.example.springkotlincoroutines.repository.ProductRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flowOf
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductIntegrationTests(
    @Autowired
    val client: WebTestClient
) {

    @MockK
    private lateinit var productRepository: ProductRepository

    @BeforeEach
    fun setUp() = MockKAnnotations.init(this)

    @Test
    fun testGetIndexProduct() {

        every {
            productRepository.findAll()
        } returns flowOf(
            Product(1, "First", "Brand", 20.00),
            Product(2, "Second", "Brand", 30.00)
        )

        client.get()
            .uri("/product")
            .exchange()
            .expectStatus().isOk
            .expectBodyList(Product::class.java)
    }
}