package med.voll.api.infra.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import med.voll.api.domain.usuario.UsusarioRepository;


@Component
public class SecurityFilter extends OncePerRequestFilter {

@Autowired
private TokenService tokenService;

@Autowired
private UsusarioRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                var tokenJWT = recuperarToken(request);
                
                if(tokenJWT != null){
                var subject = tokenService.getSubject((String) tokenJWT);
                var usuario = repository.findByLogin(subject);

                var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
                }

                filterChain.doFilter(request, response);

    }

    private Object recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader  != null){
            return authorizationHeader.replace("Bearer ","");
        }
        return null;
    }

    
}
