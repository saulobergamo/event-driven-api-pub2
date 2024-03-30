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

    fun sendRequestList(requestList: List<ProductRequest>) {
        var count = 0
        requestList.forEach { product ->
            count++
            rabbitmqProducer.sendProductRequest(product)
        }.also {
            logger.info {
                "sendRequestList: sent $count products in dataBase"
            }
        }
    }
}
