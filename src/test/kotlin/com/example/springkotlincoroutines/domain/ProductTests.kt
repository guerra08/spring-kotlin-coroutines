package com.example.springkotlincoroutines.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class ProductTests {

    @Test
    fun `should create entity from create dto without id`() {
        val dto = CreateProductDTO(
            name = "Burger",
            brand = "BK"
        )

        val entity = dto.toProductEntity()

        assertEquals(null, entity.id)
    }

    @Test
    fun `should create entity from create dto with id`() {
        val dto = CreateProductDTO(
            name = "Burger",
            brand = "BK"
        )

        val entity = dto.toProductEntity(1)

        assertEquals(1, entity.id)
    }

    @Test
    fun `should create entity from patch dto`() {
        val dto = PatchProductDTO(
            name = "Ribs",
            brand = null
        )

        val original = Product(
            id = 1,
            name = "Steak",
            brand = "Outback"
        )

        val newEntity = dto.toProductEntity(original)

        assertEquals(1, newEntity.id)
        assertEquals("Ribs", newEntity.name)
        assertEquals("Outback", newEntity.brand)
    }

}