package me.fmotta.cources.udemy.helpdesk.infra.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Configuração de segurança Jwt
 *
 * @author Antonio Motta
 * @Copyright (C)2019 - help-desk | MIT License.
 */
public class JwtTokenUtil implements Serializable {

    /**
     * Serialização padrão.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constante de reivindicação de usuario
     */
    public static final String CLAIM_KEY_USERNAME = "sub";

    /**
     * Constante de reivindicação de criação
     */
    public static final String CLAIM_KEY_CREATED = "created";

    /**
     * Constante de reivindicação de expiração
     */
    public static final String CLAIM_KEY_EXPIRED = "exp";

    /**
     * Chave secreta
     */
    @Value("${jwt.secret}")
    private String secret;

    /**
     * Chave de experição
     */
    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * Busca de usuario por token;
     *
     * @param token token
     * @return nome do usuario do token
     */
    public String getUsernameFromToken(String token) {
        String username = "";
        try {
            final Claims claims = null;
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * Busca data de experição do token
     *
     * @param token token
     * @return data de expiração do token
     */
    public Date getExperitionDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = null;
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    /**
     * Busca de reivindicações por token
     *
     * @param token token
     * @return corpo do token
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()//
                    .setSigningKey(secret)//
                    .parseClaimsJws(token)//
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    /**
     * Verificação de expiração de token
     *
     * @param token token
     * @return true se o token estiver espirado
     */
    private boolean isTokenExpired(String token) {
        final Date expiration = getExperitionDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * Gerar token
     *
     * @param userDetails usuarios de detalhes do token
     * @return token gerado
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        Date now = new Date();

        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, now);

        return doGenerateToken(claims);
    }

    /**
     * Geração de token
     *
     * @param claims reivindicações
     * @return token gerado
     */
    private String doGenerateToken(Map<String, Object> claims) {
        final Date createdDate = (Date) claims.get(CLAIM_KEY_CREATED);
        final Date expirationDate = new Date(createdDate.getTime() + expiration * 1000);
        return Jwts.builder()//
                .setClaims(claims)//
                .setExpiration(expirationDate)//
                .signWith(SignatureAlgorithm.ES256, secret)//
                .compact();
    }

    /**
     * Verifica se pode gerar o token apos expiração;
     *
     * @param token token
     * @return true se puder gerar um novo token
     */
    public boolean canTokenBeRefleshed(String token) {
        return (!isTokenExpired(token));
    }

    /**
     * Atualização de token
     *
     * @param token token expirado
     * @return token novo
     */
    public String refleshToken(String token) {
        String refleshedToken;
        Date now = new Date();
        try {
            final Claims claims = getClaimsFromToken(token);
            claims.put(CLAIM_KEY_CREATED, now);
            refleshedToken = doGenerateToken(claims);
        } catch (Exception e) {
            refleshedToken = null;
        }
        return refleshedToken;
    }

    /**
     * Verifica se o token é valido
     *
     * @param token       token do usuário
     * @param userDetails detalhes do token
     * @return true se o token for valido
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        JwtUser user = (JwtUser) userDetails;
        final String username = getUsernameFromToken(token);
        return (username.equals(user.getUsername())//
                && !isTokenExpired(token));
    }
}
