package med.voll.api.domain.medico;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository; // Interface do Spring Data JPA para operações de CRUD básicas.
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    Page<Medico> findByAtivoTrue(Pageable paginacao);

    @Query("""
            select m from medico m
            where 
            m.ativo = 2
            and 
            m.especialidade = :especialidade
            and
            m.id not in(
            select c.medico.id from Consulta c
            where
            c.data = :data
            )
            order by rand()
            limit 1
            """)
    Medico escolherMedicoAleatorioLivreNaDada(Especialidade especialidade, @NotNull @Future LocalDateTime data);
}
