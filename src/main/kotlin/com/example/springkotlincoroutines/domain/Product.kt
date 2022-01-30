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

/**
 * Extension functions regarding Product
 */
fun CreateProductDTO.toProductEntity(id: Long? = null) = Product(
    id = id,
    name = this.name,
    brand = this.brand
)