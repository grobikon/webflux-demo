package ru.grobikon.webfluxdemo

import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

class MonoFluxTest {

    @Test
    fun testMono(){
        val monoString: Mono<String> = Mono.just("my mono text").log()

        monoString.subscribe(::println)
    }

    @Test
    fun testMono2(){
        val monoString: Mono<*> = Mono.just("my mono text")
            .then(Mono.error<Any>(RuntimeException("Exception test")))
            .log()

        monoString.subscribe(
            { x: Any? -> println(x) },
            { e: Throwable -> println(e.message) })
    }

    @Test
    fun testMono3(){
        val fluxString = Flux.just("Spring", "Spring Boot", "Hibernate", "microservice")
            .concatWithValues("AWS")
            .concatWith(Flux.error(RuntimeException("Exception test in Flux")))
            .concatWithValues("cloud")
            .log()

        fluxString.subscribe (
            { println(it) },
            { e: Throwable -> println(e.message) }
        )
    }
}