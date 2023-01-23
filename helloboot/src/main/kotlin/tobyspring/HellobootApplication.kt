package tobyspring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HellobootApplication

fun main(args: Array<String>) {
    runApplication<HellobootApplication>(*args)
}
