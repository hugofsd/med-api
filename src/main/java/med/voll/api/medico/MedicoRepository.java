package med.voll.api.medico;

import org.springframework.data.jpa.repository.JpaRepository; // Interface do Spring Data JPA para operações de CRUD básicas.

public interface MedicoRepository extends JpaRepository<Medico, Long> {
}
