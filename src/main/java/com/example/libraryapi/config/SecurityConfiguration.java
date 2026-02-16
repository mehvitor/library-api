package com.example.libraryapi.config;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.DefaultSecurityFilterChain;

import com.example.libraryapi.security.LoginSocialSuccessHandler;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfiguration {

	@Bean
	public DefaultSecurityFilterChain securityFilter(HttpSecurity http, LoginSocialSuccessHandler successHandler) throws Exception {
		return http
				.csrf(AbstractHttpConfigurer::disable)
				.formLogin(configurer ->{
				configurer.loginPage("/login");
			})
				.authorizeHttpRequests(authorize -> {
					authorize.requestMatchers("/login/**").permitAll();
					authorize.requestMatchers(HttpMethod.POST , "/usuarios").permitAll();
					
					authorize.anyRequest().authenticated();
				})
				.oauth2Login(oauth2 -> {
					oauth2
					.loginPage("/login")
					.successHandler(successHandler);
				})
				.oauth2ResourceServer(oauth2Rs -> oauth2Rs.jwt(Customizer.withDefaults()))

				.build();
	}
	

	@Bean
	public GrantedAuthorityDefaults grantedAuthorityDefaults() {
		return new GrantedAuthorityDefaults("");
	}
	
	@Bean
	public JwtAuthenticationConverter jwtAuthenticationConverter() {
		var authoritiesConverter = new JwtGrantedAuthoritiesConverter();
		authoritiesConverter.setAuthorityPrefix("");
		
		var converter = new JwtAuthenticationConverter();
		converter.setJwtGrantedAuthoritiesConverter(authoritiesConverter);
		
		return converter;
	}
	
	@Bean
	public JWKSource<SecurityContext> jwkSource() throws Exception {
		RSAKey key = gerarChaveRSA();
		JWKSet jwkSet = new JWKSet(key);
		return new ImmutableJWKSet<>(jwkSet);
	}


	private RSAKey gerarChaveRSA() throws Exception {
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(2048);
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		
		RSAPublicKey chavePublica = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey chavePrivada = (RSAPrivateKey) keyPair.getPrivate();
		
		
		return new RSAKey.Builder(chavePublica)
				.privateKey(chavePrivada)
				.keyID(UUID.randomUUID().toString())
				.build();
	}
	
	@Bean
	public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
		return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
	}
	
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return web -> {
			 web.ignoring().requestMatchers(
					"/v2/api-docs/**",
					"/v3/api-docs/**",
					"/swagger-resources/**",
					"/swagger-ui.html",
					"/swagger-ui.**",
					"/webjars/**"
					);
			};
		}
	




}

