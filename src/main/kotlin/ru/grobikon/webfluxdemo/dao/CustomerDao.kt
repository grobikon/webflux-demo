package ru.grobikon.webfluxdemo.dao

import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import ru.grobikon.webfluxdemo.dto.Customer
import java.time.Duration
import java.util.stream.Collectors
import java.util.stream.IntStream

@Component
class CustomerDao {

    /**
     * Создаем 50 клиентов
     * И возвращаем их в списке
     */
    fun getCustomers(): List<Customer> {
        return IntStream.rangeClosed(1, 10)
            .peek{ Thread.sleep(1000) }
            .peek{ println("processing count: $it") }
            .mapToObj{ Customer(it, "customer $it")}
            .collect(Collectors.toList())
    }

    /**
     * Создаем 50 клиентов
     * И возвращаем их в списке через реактивность
     */
    fun getCustomersStream(): Flux<Customer> {
        return Flux.range(1, 10)
            .delayElements(Duration.ofSeconds(1))
            .doOnNext{ println("processing count in stream flow: $it") }
            .map{ Customer(it, "customer $it")}
    }

    /**
     * Создаем 50 клиентов
     * И возвращаем их в списке через реактивность
     */
    fun getCustomerList(): Flux<Customer> {
        return Flux.range(1, 50)
            .doOnNext{ println("processing count in stream flow: $it") }
            .map{ Customer(it, "customer $it")}
    }
}