package com.example.springkotlincoroutines.repository

import com.example.springkotlincoroutines.domain.Product
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : CoroutineCrudRepository<Product, Long> {

    fun findByBrandContainingIgnoreCase(brand: String): Flow<Product>

}