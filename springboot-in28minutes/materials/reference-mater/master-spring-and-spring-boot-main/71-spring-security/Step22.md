<!---
Current Directory : /Users/ranga/Ranga/git/00.courses/master-spring-and-spring-boot/20-spring-security
-->

## Complete Code Example

### /src/main/java/com/in28minutes/learnspringsecurity/LearnSpringSecurityApplication.java

```java
package com.in28minutes.learnspringsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LearnSpringSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearnSpringSecurityApplication.class, args);
	}

}
```

---

### /src/main/java/com/in28minutes/learnspringsecurity/basic/BasicAuthSecurityConfiguration.java

```java
package com.in28minutes.learnspringsecurity.basic;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableMethodSecurity(jsr250Enabled = true, securedEnabled = true)
public class BasicAuthSecurityConfiguration {
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests(
						auth -> {
							auth
							.anyRequest().authenticated();
						});
		
		http.sessionManagement(
						session -> 
							session.sessionCreationPolicy(
									SessionCreationPolicy.STATELESS)
						);
		
		//http.formLogin();
		http.httpBasic(withDefaults());
		
		http.csrf(csrf -> csrf.disable());
		
		http.headers(headers -> headers.frameOptions(frameOptionsConfig-> frameOptionsConfig.disable()));
		
		return http.build();
	}

//	@Bean
//	public UserDetailsService userDetailService() {
//		
//		var user = User.withUsername("in28minutes")
//			.password("{noop}dummy")
//			.roles("USER")
//			.build();
//
//		
//		var admin = User.withUsername("admin")
//				.password("{noop}dummy")
//				.roles("ADMIN")
//				.build();
//
//		return new InMemoryUserDetailsManager(user, admin);
//	}
	
	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.H2)
				.addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
				.build();
	}
	
	@Bean
	public UserDetailsService userDetailService(DataSource dataSource) {
		
		var user = User.withUsername("in28minutes")
			//.password("{noop}dummy")
			.password("dummy")
			.passwordEncoder(str -> passwordEncoder().encode(str))
			.roles("USER")
			.build();
		
		var admin = User.withUsername("admin")
				//.password("{noop}dummy")
				.password("dummy")
				.passwordEncoder(str -> passwordEncoder().encode(str))
				.roles("ADMIN", "USER")
				.build();
		
		var jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
		jdbcUserDetailsManager.createUser(user);
		jdbcUserDetailsManager.createUser(admin);

		return jdbcUserDetailsManager;
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

	
}
```

---

### /src/main/java/com/in28minutes/learnspringsecurity/jwt/JwtAuthenticationResource.java

```java
package com.in28minutes.learnspringsecurity.jwt;

import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;

//@RestController
public class JwtAuthenticationResource {
	
	private JwtEncoder jwtEncoder;
	
	public JwtAuthenticationResource(JwtEncoder jwtEncoder) {
		this.jwtEncoder = jwtEncoder;
	}
	
	@PostMapping("/authenticate") 
	public JwtRespose authenticate(Authentication authentication) {
		return new JwtRespose(createToken(authentication));
	}

	private String createToken(Authentication authentication) {
		var claims = JwtClaimsSet.builder()
								.issuer("self")
								.issuedAt(Instant.now())
								.expiresAt(Instant.now().plusSeconds(60 * 30))
								.subject(authentication.getName())
								.claim("scope", createScope(authentication))
								.build();
		
		return jwtEncoder.encode(JwtEncoderParameters.from(claims))
							.getTokenValue();
	}

	private String createScope(Authentication authentication) {
		return authentication.getAuthorities().stream()
			.map(a -> a.getAuthority())
			.collect(Collectors.joining(" "));			
	}

}

record JwtRespose(String token) {}
```

---

### /src/main/java/com/in28minutes/learnspringsecurity/jwt/JwtSecurityConfiguration.java

```java
package com.in28minutes.learnspringsecurity.jwt;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;


//@Configuration
public class JwtSecurityConfiguration {
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests(
						auth -> {
							auth.anyRequest().authenticated();
						});
		
		http.sessionManagement(
						session -> 
							session.sessionCreationPolicy(
									SessionCreationPolicy.STATELESS)
						);
		
		http.httpBasic(withDefaults());
		
		http.csrf(csrf -> csrf.disable());
		
		http.headers(headers -> headers.frameOptions(frameOptionsConfig-> frameOptionsConfig.disable()));
		
		http.oauth2ResourceServer((oauth2) -> oauth2.jwt(withDefaults()));
		
		
		return http.build();
	}
	
	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.H2)
				.addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
				.build();
	}
	
	@Bean
	public UserDetailsService userDetailService(DataSource dataSource) {
		
		var user = User.withUsername("in28minutes")
			//.password("{noop}dummy")
			.password("dummy")
			.passwordEncoder(str -> passwordEncoder().encode(str))
			.roles("USER")
			.build();
		
		var admin = User.withUsername("admin")
				//.password("{noop}dummy")
				.password("dummy")
				.passwordEncoder(str -> passwordEncoder().encode(str))
				.roles("ADMIN", "USER")
				.build();
		
		var jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
		jdbcUserDetailsManager.createUser(user);
		jdbcUserDetailsManager.createUser(admin);

		return jdbcUserDetailsManager;
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public KeyPair keyPair() {
		try {
			var keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			keyPairGenerator.initialize(2048);
			return keyPairGenerator.generateKeyPair();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	@Bean
	public RSAKey rsaKey(KeyPair keyPair) {
		
		return new RSAKey
				.Builder((RSAPublicKey)keyPair.getPublic())
				.privateKey(keyPair.getPrivate())
				.keyID(UUID.randomUUID().toString())
				.build();
	}

	@Bean
	public JWKSource<SecurityContext> jwkSource(RSAKey rsaKey) {
		var jwkSet = new JWKSet(rsaKey);
		
		return (jwkSelector, context) ->  jwkSelector.select(jwkSet);
		
	}
	
	@Bean
	public JwtDecoder jwtDecoder(RSAKey rsaKey) throws JOSEException {
		return NimbusJwtDecoder
				.withPublicKey(rsaKey.toRSAPublicKey())
				.build();
		
	}
	
	@Bean
	public JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource) {
		return new NimbusJwtEncoder(jwkSource);
	}
}










```

---

### /src/main/java/com/in28minutes/learnspringsecurity/resources/HelloWorldResource.java

```java
package com.in28minutes.learnspringsecurity.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldResource {
	
	@GetMapping("/hello-world")
	public String helloWorld() {
		return "Hello World v1";
	}

}
```

---

### /src/main/java/com/in28minutes/learnspringsecurity/resources/SpringSecurityPlayResource.java

```java
package com.in28minutes.learnspringsecurity.resources;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class SpringSecurityPlayResource {
	
	@GetMapping("/csrf-token")
	public CsrfToken retrieveCsrfToken(HttpServletRequest request) {
		return (CsrfToken) request.getAttribute("_csrf");
	}

}
```

---

### /src/main/java/com/in28minutes/learnspringsecurity/resources/TodoResource.java

```java
package com.in28minutes.learnspringsecurity.resources;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.security.RolesAllowed;

@RestController
public class TodoResource {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	private static final List<Todo> TODOS_LIST = 
			List.of(new Todo("in28minutes", "Learn AWS"),
					new Todo("in28minutes", "Get AWS Certified"));

	@GetMapping("/todos")
	public List<Todo> retrieveAllTodos() {
		return TODOS_LIST;
	}

	@GetMapping("/users/{username}/todos")
	@PreAuthorize("hasRole('USER') and #username == authentication.name")
	@PostAuthorize("returnObject.username == 'in28minutes'")
	@RolesAllowed({"ADMIN", "USER"})
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	public Todo retrieveTodosForSpecificUser(@PathVariable String username) {
		return TODOS_LIST.get(0);
	}

	@PostMapping("/users/{username}/todos")
	public void createTodoForSpecificUser(@PathVariable String username
			, @RequestBody Todo todo) {
		logger.info("Create {} for {}", todo, username);
	}

}

record Todo (String username, String description) {}
```

---

### /src/main/resources/application.properties

```properties
spring.main.banner-mode=off
logging.pattern.console= %d{MM-dd HH:mm:ss} - %logger{36} - %msg%n

#spring.security.user.name=in28minutes
#spring.security.user.password=dummy

spring.datasource.url=jdbc:h2:mem:testdb
```

---

### /src/test/java/com/in28minutes/learnspringsecurity/LearnSpringSecurityApplicationTests.java

```java
package com.in28minutes.learnspringsecurity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LearnSpringSecurityApplicationTests {

	@Test
	void contextLoads() {
	}

}
```

---
