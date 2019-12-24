package me.fmotta.cources.udemy.helpdesk.controller;

import me.fmotta.cources.udemy.helpdesk.infra.security.JwtAuthenticationRequest;
import me.fmotta.cources.udemy.helpdesk.infra.security.JwtCurrentUser;
import me.fmotta.cources.udemy.helpdesk.infra.security.JwtTokenUtil;
import me.fmotta.cources.udemy.helpdesk.model.User;
import me.fmotta.cources.udemy.helpdesk.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class AutheticationRestController {

    /**
     * Gerenciador de autenticação
     */
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    /**
     * Controller de criação do autenticação token
     *
     * @param authenticationRequest
     * @return token autenticado
     */
    @PostMapping("/api/auth")
    public ResponseEntity<?> createAutheticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        final String token = jwtTokenUtil.generateToken(userDetails);
        final User user = userService.findByEmail(authenticationRequest.getEmail());
        user.setPassword(null);
        return ResponseEntity.ok(new JwtCurrentUser(token, user));
    }

}
