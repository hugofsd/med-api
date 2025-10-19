package med.voll.api.infra.security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component // componente generico
public class SecurityFilter extends OncePerRequestFilter {

    // implmentes de OncePerRequestFilter
    // garante uma validação por request
    // Responsavel por validar token. Intercepta todas as requisições.
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //recuperar token
        var tokenJWT = recuperarToken(request);

        System.out.println("TOKEN: " + tokenJWT);

        //doFilter garante que vamos chamar a proxima requisição.
        // quer interromper não vamos chamar o doFilter
        filterChain.doFilter(request, response);
    }

    private Object recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");

        if(authorizationHeader == null){
            throw new RuntimeException("Token JWT não enviado no cabeçalho Authorizarion");
        }
                                            // tirando o Bearer por aspas vaizas
        return  authorizationHeader.replace("Bearer", "");
    }
}
