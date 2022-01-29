package com.example.springkotlincoroutines.service

import com.example.springkotlincoroutines.entity.Product
import com.example.springkotlincoroutines.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import org.springframework.stereotype.Service

@Service
class ProductService(val productRepository: ProductRepository) {

    suspend fun retrieveAllProducts(): Flow<Product> =
        productRepository.findAll()

    suspend fun getProductById(id: Long): Product? =
        productRepository.findById(id)

    suspend fun createProduct(product: Product) =
        productRepository.save(product)

    suspend fun getAllProductsByBrand(brand: String) =
        productRepository.findByBrandContainingIgnoreCase(brand)

    suspend fun deleteProduct(product: Product) =
        productRepository.delete(product)
}