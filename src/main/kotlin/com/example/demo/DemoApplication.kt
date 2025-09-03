package com.example.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@SpringBootApplication
class DemoApplication

fun main(args: Array<String>) {
//	val encoder : BCryptPasswordEncoder = BCryptPasswordEncoder()
//	var password = "pass1234"
//	println(encoder .encode(password));
//
//	password = "qwer1234"
//	println(encoder .encode(password));
//
//	password = "admin1234"
//	println(encoder .encode(password));
//
//	password = "minji5678"
//	println(encoder .encode(password));
//
//	password = "hello2025"
//	println(encoder .encode(password));

	runApplication<DemoApplication>(*args)
}