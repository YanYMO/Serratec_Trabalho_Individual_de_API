package org.serratec.praxis.repository;

import org.serratec.praxis.domain.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    Aluno findByEmail(String email);

    Aluno findByCpf(String cpf);
}
