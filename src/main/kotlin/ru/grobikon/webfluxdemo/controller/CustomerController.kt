package ru.grobikon.webfluxdemo.controller

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import ru.grobikon.webfluxdemo.dto.Customer
import ru.grobikon.webfluxdemo.service.CustomerService

@RestController
@RequestMapping("/customers")
class CustomerController(
    val customerService: CustomerService
) {

    @GetMapping
    fun getAllCustomers(): List<Customer> {
        return customerService.loadAllCustomers()
    }

    @GetMapping("/stream", produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun getAllCustomersStream(): Flux<Customer> {
        return customerService.loadAllCustomersStream()
    }
}