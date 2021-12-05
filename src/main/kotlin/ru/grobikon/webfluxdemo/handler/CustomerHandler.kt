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

    fun findCustomer(request: ServerRequest): Mono<ServerResponse> {
        val customerId = Integer.valueOf(request.pathVariable("input"))
        // dao.getCustomerList().filter(c->c.getId()==customerId).take(1).single();
        val customerMono: Mono<Customer> = customerDao.getCustomerList().filter { c -> c.id == customerId }.next()
        return ServerResponse.ok().body(customerMono, Customer::class.java)
    }

    fun saveCustomer(request: ServerRequest): Mono<ServerResponse> {
        val customerMono = request.bodyToMono(Customer::class.java)
        // dao.getCustomerList().filter(c->c.getId()==customerId).take(1).single();
        val saveResponse: Mono<String> = customerMono.map{ dto -> "${dto.id}: ${dto.name}"}
        return ServerResponse.ok().body(saveResponse, String::class.java)
    }
}