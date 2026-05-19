package org.serratec.praxis.repository;

import org.serratec.praxis.domain.PerfilSocial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilSocialRepository extends JpaRepository<PerfilSocial, Long> {

}
