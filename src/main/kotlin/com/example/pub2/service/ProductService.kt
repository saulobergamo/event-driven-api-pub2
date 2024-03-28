package com.example.pub2.service

import com.example.pub2.model.ProductRequest
import com.example.pub2.model.entity.Product
import com.example.pub2.producer.RabbitmqProducer
import com.example.pub2.repository.ProductRepository
import mu.KotlinLogging
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val rabbitmqProducer: RabbitmqProducer
) {
    private val logger = KotlinLogging.logger {}

    fun getAvailableProducts(): List<Product>? {
        return productRepository.findByAvailable(true).also {
            logger.info {
                "getAvailableProducts: trying to get available products"
            }
        }
    }

    fun showAllProducts(): List<Product>? {
        return productRepository.findAll().also {
            logger.info {
                "showAllProducts: trying to get all products"
            }
        }
    }

    fun sendOrder(productRequest: List<ProductRequest>) {
        var count = 0
        productRequest.forEach { product ->
            val newProduct = Product(
                null,
                product.available,
                product.description,
                product.price,
                product.amount
            )
            count++
            rabbitmqProducer.sendPlacedOrders(newProduct)
        }.also {
            logger.info {
                "sendOrder: sent $count products to queue"
            }
        }
    }
}
