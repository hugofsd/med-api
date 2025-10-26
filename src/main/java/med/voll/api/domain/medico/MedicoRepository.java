package med.voll.api.domain.medico;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository; // Interface do Spring Data JPA para operações de CRUD básicas.
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    Page<Medico> findByAtivoTrue(Pageable paginacao);

    // Usamos nativeQuery = true porque 'rand()' e 'limit' são funções do SQL (MySQL) e não do JPQL.
    // Também usamos o nome real das tabelas e colunas do banco de dados (ex: medicos, medico_id).
    @Query(value = """
            select * from medicos m
            where 
            m.ativo = true
            and 
            m.especialidade = :especialidade
            and
            m.id not in(
            select c.medico_id from consultas c
            where
            c.data = :data
            )
            order by rand()
            limit 1
            """, nativeQuery = true)
    Medico escolherMedicoAleatorioLivreNaData(Especialidade especialidade, @NotNull @Future LocalDateTime data);

    @Query("""
            select m.ativo
            from Medico m
            where
            m.id = :id
            """)
    Boolean findAtivoById(Long id);

}
