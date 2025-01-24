package com.micoder.sbdemo

import com.micoder.sbdemo.security.CustomAccessDeniedHandler
import com.micoder.sbdemo.security.CustomAuthenticationEntryPoint
import com.micoder.sbdemo.security.JwtAuthenticationFilter
import com.micoder.sbdemo.service.web.WebService
import jakarta.persistence.EntityManagerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableMethodSecurity
@EnableTransactionManagement
class SecurityConfig(
    private val jwtAuthenticationFilter: JwtAuthenticationFilter,
    private val authenticationEntryPoint: CustomAuthenticationEntryPoint,
    private val accessDeniedHandler: CustomAccessDeniedHandler,
    private val webService: WebService,
    private val passwordEncoder: PasswordEncoder
) {

    @Bean
    fun transactionManager(entityManagerFactory: EntityManagerFactory): PlatformTransactionManager {
        return JpaTransactionManager(entityManagerFactory)
    }

    @Bean
    fun authenticationManager(authConfig: AuthenticationConfiguration): AuthenticationManager {
        return authConfig.authenticationManager
    }

    /**
     * Security filter chain for API endpoints.
     * High priority to ensure API requests are handled separately.
     */
    @Bean
    @Order(1)
    fun apiSecurityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http {
            securityMatcher("/v1/api/**")
            csrf { disable() }
            sessionManagement { sessionCreationPolicy = SessionCreationPolicy.STATELESS }
            authorizeHttpRequests {
                authorize("/v1/api/auth/**", permitAll)
                authorize("/v1/api/admin/**", hasRole("ADMIN"))
                authorize("/v1/api/user/**", hasAnyRole("USER", "ADMIN"))
                authorize(anyRequest, authenticated)
            }
            exceptionHandling {
                authenticationEntryPoint = this@SecurityConfig.authenticationEntryPoint
                accessDeniedHandler = this@SecurityConfig.accessDeniedHandler
            }
            addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
        }

        return http.build()
    }

    @Bean
    fun userDetailsService(): UserDetailsService = webService

    @Bean
    fun authenticationProvider(): AuthenticationProvider {
        return DaoAuthenticationProvider().apply {
            setUserDetailsService(webService)
            setPasswordEncoder(passwordEncoder)
        }
    }

    @Bean
    @Order(2)  // Lower priority for form login
    fun webSecurityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http {
            httpBasic { disable() }
            csrf { disable() }
            authorizeHttpRequests {
                authorize("/req/login", permitAll)
                authorize("/req/signup", permitAll)
                authorize("/css/**", permitAll)
                authorize("/error/**", permitAll)
                authorize("/js/**", permitAll)

                authorize("/", hasRole("ADMIN"))

                authorize(anyRequest, authenticated)
            }
            formLogin {
                loginPage = "/req/login"
                defaultSuccessUrl("/", true)
                permitAll()
            }
            logout {
                logoutUrl = "/req/logout"
                invalidateHttpSession = true
                deleteCookies("JSESSIONID")
                logoutSuccessUrl = "/req/login?logout"
            }
            exceptionHandling {
                accessDeniedPage = "/static/error/404" // Custom error page
            }
        }
        return http.build()
    }

}