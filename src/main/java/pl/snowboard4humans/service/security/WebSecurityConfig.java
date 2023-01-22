package pl.snowboard4humans.service.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()
        .withUser("daw.bernacki@gmail.com")
        .password(passwordEncoder().encode("dawidek"))
        .roles("USER")
        .and()
        .withUser("admin@snowboard4humans.pl")
        .password(passwordEncoder().encode("password"))
        .roles("ADMIN");
  }

  @Override
  protected void configure(final HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .authorizeRequests()
        .antMatchers("/admin/**").hasAnyRole("ADMIN")
        .antMatchers("/homepage/reviews/**").hasAnyRole("USER")
        .antMatchers("/").permitAll()
        .and()
        .formLogin()
//                .loginPage("/homepage/login")
//                .permitAll()
        .and()
        .logout()
        .logoutSuccessUrl("/")
        .permitAll()
        .and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
