package med.voll.api.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository; // Interface do Spring Data JPA para operações de CRUD básicas.

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    Page<Medico> findByAtivoTrue(Pageable paginacao);
}
