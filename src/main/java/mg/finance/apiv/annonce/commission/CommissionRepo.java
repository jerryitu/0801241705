package mg.finance.apiv.annonce.commission;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommissionRepo extends JpaRepository<Commission,Integer> {
    Optional<Commission> findCommissionByIdCategorie(Integer idCategorie);
}
