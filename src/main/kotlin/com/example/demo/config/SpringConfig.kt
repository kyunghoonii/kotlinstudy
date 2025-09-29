package com.example.demo.config

import org.springframework.boot.autoconfigure.security.servlet.PathRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SpringConfig {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            // H2 콘솔은 CSRF 제외
            //.csrf { it.ignoringRequestMatchers(PathRequest.toH2Console()) }
            .csrf { it.disable() }
            // H2 콘솔이 frame을 쓰므로 sameOrigin 허용
            .headers { it.frameOptions { f -> f.sameOrigin() } }
            // H2 콘솔만 열어주고 나머지는 원하는 정책으로
            .authorizeHttpRequests {
//                it.requestMatchers(PathRequest.toH2Console()).permitAll()
                it.requestMatchers("/actuator/health", "/actuator/info").permitAll()
                it.requestMatchers("/actuator/**").permitAll()  // 내부망이라면 permitAll, 외부면 ROLE 제한
                it.anyRequest().permitAll() // 필요하면 authenticated() 로 바꾸세요
            }

        return http.build()
    }

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }
}