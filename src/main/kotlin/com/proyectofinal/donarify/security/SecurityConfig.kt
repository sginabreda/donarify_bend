package com.proyectofinal.donarify.security

import com.proyectofinal.donarify.service.UserService
import java.util.Collections
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true)
class SecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var jwtEntryPoint: JwtAuthenticationEntryPoint

    @Autowired
    private lateinit var jwtFilter: JwtFilter

    private val permitMethods =
        Collections.unmodifiableList(
            listOf(
                HttpMethod.GET.name,
                HttpMethod.HEAD.name,
                HttpMethod.POST.name,
                HttpMethod.PUT.name,
                HttpMethod.DELETE.name
            )
        )

    @Autowired
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder())
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    override fun configure(http: HttpSecurity?) {
        http!!.cors()
            .configurationSource {
                val config = CorsConfiguration()
                config.allowedMethods = permitMethods
                config.applyPermitDefaultValues()
            }
            .and()
            .csrf()
            .disable()
            .authorizeRequests()
            .antMatchers("/users/login", "/users/register").permitAll()
            .antMatchers(HttpMethod.GET, "/organizations/**").permitAll()
            .antMatchers(HttpMethod.GET, "/posts/**").permitAll()
            .antMatchers(HttpMethod.GET, "/campaigns/**").permitAll()
            .anyRequest().authenticated().and()
            .exceptionHandling().authenticationEntryPoint(jwtEntryPoint).and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)
    }
}
