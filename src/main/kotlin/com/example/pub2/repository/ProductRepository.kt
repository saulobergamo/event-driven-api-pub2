package com.example.pub2.repository

import com.example.pub2.model.entity.Product
import org.springframework.data.mongodb.repository.MongoRepository

interface ProductRepository : MongoRepository<Product, String> {

    fun findByAvailable(available: Boolean): List<Product>?
}
