package med.voll.api.infra.security;

import java.time.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import med.voll.api.domain.usuario.Usuario;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(Usuario usuario){

                try {
            var algoritimo = Algorithm.HMAC256(secret);
            return JWT.create()
                .withIssuer("API Voll.med")
                .withSubject(usuario.getLogin())
                .withExpiresAt(dataExpiracao())
                .sign(algoritimo);
        } catch (JWTCreationException exception){
           throw new RuntimeException("erro ao gerar token jwt", exception);
        }

    }

    private Instant dataExpiracao() {
       return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public String getSubject(String tokenJWT) {
            try {
                var algoritmo = Algorithm.HMAC256(secret);
                return JWT.require(algoritmo)
                                .withIssuer("API Voll.med")
                                .build()
                                .verify(tokenJWT)
                                .getSubject();
            } catch (JWTVerificationException exception) {
                throw new RuntimeException("Token JWT inválido ou expirado!");
            }
    }
}
