package com.dden.todo.config;

import com.dden.todo.directory.Person;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.EntityModel;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.http.HttpStatus;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@EnableConfigurationProperties(ToDoProperties.class)
@Configuration
public class ToDoSecurityConfig{

    private final Logger log = LoggerFactory.getLogger(ToDoSecurityConfig.class);

    //It can be used to connect to app Directory
    private RestTemplate restTemplate;
    private ToDoProperties toDoProperties;
    private UriComponentsBuilder builder;

    public ToDoSecurityConfig(RestTemplateBuilder restTemplateBuilder, ToDoProperties toDoProperties){
        this.toDoProperties = toDoProperties;
        this.restTemplate = restTemplateBuilder.basicAuthentication(toDoProperties.getUsername(),
                toDoProperties.getPassword()).build();
    }
//    @Bean
//    public UserDetailsService userDetailsService(BCryptPasswordEncoder bCryptPasswordEncoder) {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("dden")
//                .password(bCryptPasswordEncoder.encode("as"))
//                .roles("ADMIN", "USER")
//                .build());
//        return manager;
//    }
//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder(){
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .userDetailsService(new UserDetailsService() {
                    @Override
                    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                        try {
                            builder = UriComponentsBuilder
                                    .fromUriString(toDoProperties.getFindByEmailUri())
                                    .queryParam("email", username);
                            log.info("Querying: " + builder.toUriString());
                            ResponseEntity<EntityModel<Person>> responseEntity = restTemplate.exchange(
                                    RequestEntity.get(URI.create(builder.toUriString()))
                                            .accept(MediaTypes.HAL_JSON)
                                            .build(),
                                    new ParameterizedTypeReference<EntityModel<Person>>() {
                                    }
                            );

                            if (responseEntity.getStatusCode() == HttpStatus.OK){
                                EntityModel<Person> resource = responseEntity.getBody();
                                Person person = resource.getContent();

                                PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
                                String password = encoder.encode(person.getPassword());

                                return User
                                        .withUsername(person.getEmail())
                                        .password(password)
                                        .accountLocked(!person.isEnabled())
                                        .roles(person.getRole()).build();
                            }
                        }catch (Exception ex){
                            ex.printStackTrace();
                        }
                        throw new UsernameNotFoundException(username);
                    }
                })
                .authorizeHttpRequests(restTemplate-> restTemplate
                        .requestMatchers(PathRequest
                                .toStaticResources()
                                .atCommonLocations()).permitAll()
                        .requestMatchers("/","/api/**")
                        .hasAnyRole("USER", "ADMIN"))
//                .authorizeRequests()
//                .requestMatchers(
//                        PathRequest
//                                .toStaticResources()
//                                .atCommonLocations()).permitAll()
//                .requestMatchers("/","/api/**").hasRole("USER")
//                .and()
                .formLogin(f -> f
                        .loginPage("/login").permitAll())
                .logout(l -> l
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login"))
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }



//                .authorizeHttpRequests((auth) -> auth
//                        .requestMatchers(PathRequest
//                                .toStaticResources()
//                                .atCommonLocations()).permitAll()
//
//                )
//                .
//                .formLogin(f -> f
//                        .loginPage("/login").permitAll())
//                .logout(l -> l
//                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                        .logoutSuccessUrl("/login"))
//                .httpBasic(Customizer.withDefaults());
}
