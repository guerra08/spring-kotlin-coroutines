package com.example.springkotlincoroutines.controller

import com.example.springkotlincoroutines.entity.Product
import com.example.springkotlincoroutines.service.ProductService
import kotlinx.coroutines.flow.Flow
import org.springframework.web.bind.annotation.*

@RestController
class ProductController(val productService: ProductService) {

    data class PostProductDTO(
        val name: String,
        val brand: String
    )

    @GetMapping("/product")
    suspend fun getIndex(): Flow<Product> {
        return productService.retrieveAllProducts()
    }

    @GetMapping("/product/{id}")
    suspend fun getProduct(@PathVariable id: Long): Product? {
        return productService.getProductById(id)
    }

    @PostMapping("/product")
    suspend fun postProduct(@RequestBody body: PostProductDTO): Product {
        return productService.createProduct(
            Product(
                id = null,
                name = body.name,
                brand = body.brand
            )
        )
    }

}