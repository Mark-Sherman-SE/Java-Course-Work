package main.security;

import main.entity.User;
import main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import java.time.LocalDateTime;

@Configuration
@EnableWebSecurity
@EnableOAuth2Sso
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private JwtTokenProvider jwtTokenProvider;
//
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.httpBasic().disable()
//                .csrf().disable()
//                .formLogin().disable()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeRequests()
//                .antMatchers("/auth/signin").permitAll()
//                .antMatchers("/", "/js/**").permitAll()
//                .antMatchers("/home/article/**").permitAll()
//                .antMatchers(HttpMethod.GET, "/home/balance/**", "/home/operation/**").hasAnyRole("ADMIN", "USER")
//                .antMatchers(HttpMethod.POST, "/home/balance/addBalance", "/home/operation/addOperation").hasAnyRole("ADMIN", "USER")
//                .antMatchers(HttpMethod.DELETE, "/home/balance/{id}", "/home/operation/{id}").hasAnyRole("ADMIN", "USER")
//                .antMatchers(HttpMethod.PUT, "/home/article/{id}").hasAnyRole("ADMIN", "USER")
//                .anyRequest().authenticated()
//                .and()
//                .apply(new JwtSecurityConfigurer(jwtTokenProvider));
        http
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/", "/login**", "/js/**", "/error**").permitAll()
                .anyRequest().authenticated()
                .and().logout().logoutSuccessUrl("/").permitAll()
                .and()
                .csrf().disable();
    }

    @Bean
    public PrincipalExtractor principalExtractor(UserRepository userRepository) {
        return map -> {
            String id = (String) map.get("sub");
            User user = userRepository.findById(id).orElseGet(() -> {
               User newUser = new User();

               newUser.setId(id);
               newUser.setName((String) map.get("name"));
               newUser.setEmail((String) map.get("email"));
               newUser.setGender((String) map.get("gender"));
               newUser.setLocale((String) map.get("locale"));
               newUser.setUserpic((String) map.get("picture"));
               return newUser;
            });
            user.setLastVisit(LocalDateTime.now());
            return userRepository.save(user);
        };
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("user").password("{noop}pwd").roles("USER")
//                .and()
//                .withUser("admin").password("{noop}apwd").roles("ADMIN");
//    }
}
