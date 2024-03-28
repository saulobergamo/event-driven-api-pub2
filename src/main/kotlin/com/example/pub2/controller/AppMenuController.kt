package com.example.pub2.controller

import com.example.pub2.model.ProductRequest
import com.example.pub2.model.entity.Product
import com.example.pub2.service.ProductService
import io.swagger.v3.oas.annotations.tags.Tag
import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@Tag(name = "AppMenuController")
@RequestMapping("/event-driven-api-pub1")
class AppMenuController(
    private val productService: ProductService,
) {
    private val logger = KotlinLogging.logger {}

    @GetMapping
    fun getAvailableMenu(): List<Product>? {
        return productService.getAvailableProducts()
    }

    @PostMapping
    fun placeOrder(@RequestBody productRequest: List<ProductRequest>): ResponseEntity<String> {
        productService.sendOrder(productRequest).also {
            logger.info {
                "placeOrder: request sent successfully"
            }
        }
        return ResponseEntity.ok("placeOrder: order placed correctly")
    }
}
