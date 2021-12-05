package ru.grobikon.webfluxdemo.service

import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import ru.grobikon.webfluxdemo.dao.CustomerDao
import ru.grobikon.webfluxdemo.dto.Customer

@Service
class CustomerService(
    val customerDao: CustomerDao
) {

    fun loadAllCustomers(): List<Customer> {
        val start = System.currentTimeMillis()
        val customers = customerDao.getCustomers()
        val end = System.currentTimeMillis()
        println("Время получения данных: ${end - start}")
        return customers
    }

    fun loadAllCustomersStream(): Flux<Customer> {
        val start = System.currentTimeMillis()
        val customers = customerDao.getCustomersStream()
        val end = System.currentTimeMillis()
        println("Время получения данных: ${end - start}")
        return customers
    }
}