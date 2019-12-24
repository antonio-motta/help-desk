package me.fmotta.cources.udemy.helpdesk.infra.config;

import me.fmotta.cources.udemy.helpdesk.infra.security.JwtAuthenticationEntryPoint;
import me.fmotta.cources.udemy.helpdesk.infra.security.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configuração de autenticação Jwt
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Implementação Jwt
     */
    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    /**
     * Serviço de usuarios Jwt
     */
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * Configuração Jwt
     *
     * @param authenticationManagerBuilder
     */
    @Autowired
    public void configureAuthetication(AuthenticationManagerBuilder authenticationManagerBuilder) {
        try {
            authenticationManagerBuilder.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Criptografia Jwt
     *
     * @return criptografia
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Filter bean jwt
     *
     * @return filter bean
     */
    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() {
        return new JwtAuthenticationTokenFilter();
    }

    /**
     * Configuração Jwt
     *
     * @param httpSecurity servlet de segurança
     */
    protected void configure(HttpSecurity httpSecurity) {
        try {
            httpSecurity.csrf().disable()//
                    .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()//
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()//
                    .authorizeRequests()//
                    .antMatchers(HttpMethod.GET,
                            "/",
                            "/*.html",
                            "/favicon.ico",
                            "/**/*.html",
                            "/**/*.css",
                            "/**/*.js").permitAll()//
                    .antMatchers("/api/auth/**").permitAll()//
                    .anyRequest().authenticated();
            httpSecurity.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

            httpSecurity.headers().cacheControl();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
