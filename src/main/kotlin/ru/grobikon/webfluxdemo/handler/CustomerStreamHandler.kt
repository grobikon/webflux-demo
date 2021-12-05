package ru.grobikon.webfluxdemo.handler

import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import ru.grobikon.webfluxdemo.dao.CustomerDao
import ru.grobikon.webfluxdemo.dto.Customer


@Service
class CustomerStreamHandler(
    val customerDao: CustomerDao
) {

    fun loadCustomers(request: ServerRequest): Mono<ServerResponse> {
        val customerList = customerDao.getCustomersStream()
        return ServerResponse.ok()
            .contentType(MediaType.TEXT_EVENT_STREAM)
            .body(customerList, Customer::class.java)
    }
}