package com.example.springkotlincoroutines.handler

import com.example.springkotlincoroutines.domain.CreateProductDTO
import com.example.springkotlincoroutines.domain.Product
import com.example.springkotlincoroutines.repository.ProductRepository
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*

/**
 * This class contains all the handler functions regarding Product
 */
@Component
class ProductHandler(
    private val productRepository: ProductRepository
) {

    suspend fun getAllProducts(req: ServerRequest): ServerResponse {
        return ServerResponse
            .ok()
            .json()
            .bodyAndAwait(
                productRepository.findAll()
            )
    }

    suspend fun getProductById(req: ServerRequest): ServerResponse {
        val id = req.pathVariable("id").toLong()
        return productRepository.findById(id)?.let {
            ServerResponse
                .ok()
                .json()
                .bodyValueAndAwait(it)
        } ?: ServerResponse.notFound().buildAndAwait()
    }

    suspend fun createProduct(req: ServerRequest): ServerResponse {
        val productFromBody = req.awaitBodyOrNull(CreateProductDTO::class)
        return productFromBody?.let {
            ServerResponse
                .ok()
                .json()
                .bodyValueAndAwait(
                    productRepository.save(
                        Product(
                            id = null,
                            name = productFromBody.name,
                            brand = productFromBody.brand
                        )
                    )
                )
        } ?: ServerResponse.badRequest().buildAndAwait()
    }

    suspend fun deleteProduct(req: ServerRequest): ServerResponse {
        val id = req.pathVariable("id").toLong()
        return productRepository.findById(id)?.let {
            ServerResponse
                .ok()
                .json()
                .bodyValueAndAwait(
                    productRepository.delete(it)
                )
        } ?: ServerResponse.notFound().buildAndAwait()
    }

}