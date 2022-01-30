package com.example.springkotlincoroutines.routes

import com.example.springkotlincoroutines.handler.ProductHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

/**
 * This class contains all the HTTP routes for the application
 */
@Configuration
class Routes(
    private val productHandler: ProductHandler
) {
    @Bean
    fun routes() = coRouter {
        "/product".nest {
            GET("", productHandler::getAllProducts)
            POST("", productHandler::createProduct)

            "/{id}".nest {
                GET("", productHandler::getProductById)
                DELETE("", productHandler::deleteProduct)
            }
        }
    }
}