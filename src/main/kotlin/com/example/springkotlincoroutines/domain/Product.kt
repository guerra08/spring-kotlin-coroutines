package com.example.springkotlincoroutines.domain

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import javax.validation.constraints.NotEmpty

@Table("products")
@JsonSerialize
data class Product(
    @Id
    val id: Long?,
    @field:NotEmpty
    val name: String,
    @field:NotEmpty
    val brand: String,
    val price: Double
)

data class CreateProductDTO(
    @field:NotEmpty
    val name: String,
    @field:NotEmpty
    val brand: String,
    val price: Double
)

data class PatchProductDTO(
    val name: String?,
    val brand: String?,
    val price: Double?
)

/**
 * Extension functions regarding Product
 */
fun CreateProductDTO.toProductEntity(id: Long? = null) = Product(
    id = id,
    name = this.name,
    brand = this.brand,
    price = this.price
)

fun PatchProductDTO.toProductEntity(original: Product) = Product(
    id = original.id,
    name = this.name ?: original.name,
    brand = this.brand ?: original.brand,
    price = this.price ?: original.price
)