package com.example.springkotlincoroutines.controller

import com.example.springkotlincoroutines.entity.PatchProductDTO
import com.example.springkotlincoroutines.entity.CreateProductDTO
import com.example.springkotlincoroutines.entity.Product
import com.example.springkotlincoroutines.service.ProductService
import kotlinx.coroutines.flow.Flow
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class ProductController(val productService: ProductService) {

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
    suspend fun postProduct(@RequestBody body: CreateProductDTO): Product {
        return productService.createProduct(body)
    }

    @DeleteMapping("/product/{id}")
    suspend fun deleteProduct(@PathVariable id: Long): ResponseEntity<Product> {
        return productService.getProductById(id)?.let {
            productService.deleteProduct(it)
            ResponseEntity.noContent().build()
        } ?: ResponseEntity.notFound().build()
    }

    @PutMapping("product/{id}")
    suspend fun putProduct(
        @PathVariable id: Long,
        @RequestBody body: CreateProductDTO
    ): ResponseEntity<Product> {
        return productService.getProductById(id)?.let {
            ResponseEntity.ok(
                productService.putProduct(id, body)
            )
        } ?: ResponseEntity.notFound().build()
    }

    @PatchMapping("/product/{id}")
    suspend fun patchProduct(
        @PathVariable id: Long,
        @RequestBody body: PatchProductDTO
    ): ResponseEntity<Product> {
        return productService.getProductById(id)?.let {
            ResponseEntity.ok(
                productService.patchProduct(it, body)
            )
        } ?: ResponseEntity.notFound().build()
    }
}