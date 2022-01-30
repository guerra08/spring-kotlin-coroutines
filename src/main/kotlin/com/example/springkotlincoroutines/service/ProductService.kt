package com.example.springkotlincoroutines.service

import com.example.springkotlincoroutines.entity.CreateProductDTO
import com.example.springkotlincoroutines.entity.PatchProductDTO
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

    suspend fun createProduct(product: CreateProductDTO) =
        productRepository.save(
            Product(
                id = null,
                name = product.name,
                brand = product.brand
            )
        )

    suspend fun getAllProductsByBrand(brand: String) =
        productRepository.findByBrandContainingIgnoreCase(brand)

    suspend fun deleteProduct(product: Product) =
        productRepository.delete(product)

    suspend fun putProduct(
        id: Long,
        product: CreateProductDTO
    )
            : Product {
        return productRepository.save(
            Product(
                id = id,
                name = product.name,
                brand = product.brand
            )
        )
    }

    suspend fun patchProduct(
        original: Product,
        newFields: PatchProductDTO
    ): Product {
        val patchedProduct = Product(
            id = original.id,
            name = newFields.name ?: original.name,
            brand = newFields.brand ?: original.brand
        )
        return productRepository.save(patchedProduct)
    }

}