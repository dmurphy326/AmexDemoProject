package com.dmurphy

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AmexDemoProjectApplication

fun main(args: Array<String>) {
	runApplication<AmexDemoProjectApplication>(*args)
}
