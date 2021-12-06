package ru.grobikon.webfluxdemo.router

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse
import ru.grobikon.webfluxdemo.handler.CustomerHandler
import ru.grobikon.webfluxdemo.handler.CustomerStreamHandler

@Configuration
class RouterConfig(
    val customerHandler: CustomerHandler,
    val customerStreamHandler: CustomerStreamHandler
) {

    /**
     * Функция маршрутизатора,
     *
     * Составляем карту обработчика
     */
    @Bean
    fun routerFunction(): RouterFunction<ServerResponse> {
        return RouterFunctions.route()
            .GET("/router/customers", customerHandler::loadCustomers)                   //возвращаем список клиентов
            .GET("/router/customers/stream", customerStreamHandler::loadCustomers)      //возвращаем список клиентов stream
            .GET("/router/customer/{input}", customerHandler::findCustomer)             //поиск клиента
            .POST("/router/customer/save", customerHandler::saveCustomer)               //сохранение нового клиента
            .build()
    }
}