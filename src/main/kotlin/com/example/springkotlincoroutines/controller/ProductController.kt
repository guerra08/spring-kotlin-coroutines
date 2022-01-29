package com.example.springkotlincoroutines.controller

import com.example.springkotlincoroutines.entity.Product
import com.example.springkotlincoroutines.service.ProductService
import kotlinx.coroutines.flow.Flow
import org.springframework.http.ResponseEntity
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
    suspend fun getProduct(@PathVariable id: Long): ResponseEntity<Product> {
        return productService.getProductById(id)?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity.notFound().build()
    }

    @GetMapping("/product/brand/{brand}")
    suspend fun getAllProductsByBrand(@PathVariable brand: String): Flow<Product> {
        return productService.getAllProductsByBrand(brand)
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

    @DeleteMapping("/product/{id}")
    suspend fun deleteProduct(@PathVariable id: Long): ResponseEntity<Product> {
        return productService.getProductById(id)?.let {
            productService.deleteProduct(it)
            ResponseEntity.noContent().build()
        } ?: ResponseEntity.notFound().build()
    }

}