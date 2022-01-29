package com.example.springkotlincoroutines.repository

import com.example.springkotlincoroutines.entity.Product
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

@org.springframework.stereotype.Repository
interface ProductRepository : CoroutineCrudRepository<Product, Long> { }