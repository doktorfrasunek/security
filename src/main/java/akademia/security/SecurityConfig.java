package akademia.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration // ctrl+o dostępne metody do przeciążenia
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private PasswordEncoder passwordEncoder;

    // PasswordEncoder not a been => Security Aplication
    public SecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .passwordEncoder(passwordEncoder)
                .withUser("user")
                .password("$2a$10$RDZG9LHe9hOppFF9TohHz.tbiSwh.5Et8DRppc7CdHxQNd0KLljjC")
                .roles("USER")
                .and()
                .withUser("admin")
                .password("$2a$10$Ytc9t0s4XiJhUQlYrF.DBemRJuQ7M5CptrTzMIZ7zjkG4XeTK2pOm")
                .roles("ADMIN","USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/user").hasAnyRole("ADMIN","USER")
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .defaultSuccessUrl("/");
    }
}