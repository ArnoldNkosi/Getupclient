package za.co.metropolitan.getup.client.config;;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

 private static final String[] AUTH_WHITELIST = {
         // -- Swagger UI v2
         "/v2/api-docs",
         "/swagger-resources",
         "/swagger-resources/**",
         "/configuration/ui",
         "/configuration/security",
         "/swagger-ui.html",
         "/webjars/**",
         // -- Swagger UI v3 (OpenAPI)
         "/v3/api-docs/**",
         "/swagger-ui/**"
         // other public endpoints of your API may be appended to this array
 };

 @Override
 protected void configure(HttpSecurity http) throws Exception {
  // Enable CORS and disable CSRF
  http = http.cors().and().csrf().disable();


  // Set unauthorized requests exception handler
  http = http
          .exceptionHandling()
          .authenticationEntryPoint(
                  (request, response, ex) -> {
                   response.sendError(
                           HttpServletResponse.SC_UNAUTHORIZED,
                           ex.getMessage()
                   );
                  }
          )
          .and();

  // Set permissions on endpoints
//  http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
  http.authorizeRequests().antMatchers(AUTH_WHITELIST).permitAll().anyRequest().authenticated().and().httpBasic();

 }

 // Used by spring security if CORS is enabled.
 @Bean
 public CorsFilter corsFilter() {
  UrlBasedCorsConfigurationSource source =
          new UrlBasedCorsConfigurationSource();
  CorsConfiguration config = new CorsConfiguration();
  config.setAllowCredentials(true);
  config.addAllowedOrigin("*");
  config.addAllowedHeader("*");
  config.addAllowedMethod("*");
  source.registerCorsConfiguration("/**", config);
  return new CorsFilter(source);
 }



}


