package com.example.springkotlincoroutines.domain

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("products")
@JsonSerialize
data class Product(
    @Id
    val id: Long?,
    val name: String,
    val brand: String
)

data class CreateProductDTO(
    val name: String,
    val brand: String
)

data class PatchProductDTO(
    val name: String?,
    val brand: String?
)