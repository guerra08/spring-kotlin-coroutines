package com.example.springkotlincoroutines.handler

import com.example.springkotlincoroutines.domain.CreateProductDTO
import com.example.springkotlincoroutines.domain.PatchProductDTO
import com.example.springkotlincoroutines.domain.toProductEntity
import com.example.springkotlincoroutines.repository.ProductRepository
import org.springframework.stereotype.Component
import org.springframework.validation.BeanPropertyBindingResult
import org.springframework.validation.Validator
import org.springframework.web.reactive.function.server.*
import org.springframework.web.server.ServerWebInputException

/**
 * This class contains all the handler functions regarding Product
 */
@Component
class ProductHandler(
    private val productRepository: ProductRepository,
    private val validator: Validator
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
        val productFromBody = req.awaitBody(CreateProductDTO::class)
        validateCreateProductDTO(productFromBody)
        return ServerResponse
            .ok()
            .json()
            .bodyValueAndAwait(
                productRepository.save(productFromBody.toProductEntity())
            )
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

    suspend fun putProduct(req: ServerRequest): ServerResponse {
        val id = req.pathVariable("id").toLong()
        val productFromBody = req.awaitBody(CreateProductDTO::class)
        validateCreateProductDTO(productFromBody)
        return productRepository.findById(id)?.let {
            ServerResponse
                .ok()
                .json()
                .bodyValueAndAwait(
                    productRepository.save(productFromBody.toProductEntity(id))
                )
        } ?: ServerResponse.notFound().buildAndAwait()
    }

    suspend fun patchProduct(req: ServerRequest): ServerResponse {
        val id = req.pathVariable("id").toLong()
        val productFromBody = req.awaitBody(PatchProductDTO::class)

        return productRepository.findById(id)?.let { original ->
            ServerResponse
                .ok()
                .json()
                .bodyValueAndAwait(
                    productRepository.save(
                        productFromBody.toProductEntity(original)
                    )
                )
        } ?: ServerResponse.notFound().buildAndAwait()
    }

    /**
     * Validator functions
     */
    private fun validateCreateProductDTO(dto: CreateProductDTO) {
        val errors = BeanPropertyBindingResult(dto, "dto")
        validator.validate(dto, errors)
        if (errors.hasErrors())
            throw ServerWebInputException(errors.toString())
    }

}