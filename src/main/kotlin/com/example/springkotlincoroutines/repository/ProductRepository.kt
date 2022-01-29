package com.example.springkotlincoroutines.repository

import com.example.springkotlincoroutines.entity.Product
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : CoroutineCrudRepository<Product, Long> { }