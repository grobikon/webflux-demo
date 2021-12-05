package ru.grobikon.webfluxdemo.handler

import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import ru.grobikon.webfluxdemo.dao.CustomerDao
import ru.grobikon.webfluxdemo.dto.Customer

@Service
class CustomerHandler(
    val customerDao: CustomerDao
) {

    fun loadCustomers(request: ServerRequest): Mono<ServerResponse> {
        val customerList = customerDao.getCustomerList()
        return ServerResponse.ok().body(customerList, Customer::class.java)
    }
}