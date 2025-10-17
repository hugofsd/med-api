package med.voll.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import med.voll.api.domain.usuario.Usuario;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {
    // base dadocumentação
    public String gerarToken(Usuario usuario){
        try {
           var  algoritiomo = Algorithm.HMAC256("12345678");
            return JWT.create()
                    .withIssuer("API Voll.med") // quem está fazendo, sera armazenado no token
                    .withSubject(usuario.getLogin())
                    //.withClaim("id", usuario.getId())//withClaim posso colocar qualquer info dentro do token
                    .withExpiresAt(dataExpiracao()) // expiração dp tpken
                    .sign(algoritiomo);
        } catch (JWTCreationException exception){
          throw new RuntimeException("erro ao gerar token jwt", exception);
        }
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
