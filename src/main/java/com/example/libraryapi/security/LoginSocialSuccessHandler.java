package com.example.libraryapi.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.example.libraryapi.model.Usuario;
import com.example.libraryapi.service.UsuarioService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LoginSocialSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	
	
	private final UsuarioService usuarioService;
	
	public LoginSocialSuccessHandler(UsuarioService usuarioService) {
		super();
		this.usuarioService = usuarioService;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		OAuth2AuthenticationToken auth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
		OAuth2User oAuth2User = auth2AuthenticationToken.getPrincipal();
		
		String email = oAuth2User.getAttribute("email");
		
		Usuario usuario = usuarioService.obterPorEmaul(email);
		
		authentication = new CustomAuthentication(usuario);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		super.onAuthenticationSuccess(request, response, authentication);
	}
		
}
