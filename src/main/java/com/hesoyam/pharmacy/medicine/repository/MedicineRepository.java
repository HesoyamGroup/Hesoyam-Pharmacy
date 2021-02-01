package com.hesoyam.pharmacy.medicine.repository;

import com.hesoyam.pharmacy.medicine.DTO.MedicineSearchDTO;
import com.hesoyam.pharmacy.medicine.model.Medicine;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    List<Medicine> findAllByNameLike(String name);
    @Query("SELECT med FROM Medicine med WHERE " +
            "(:#{#filter.name} IS NULL OR med.name LIKE CONCAT('%', CONCAT(cast(:#{#filter.name} as string), '%')) )" +//Think about removing first % so we can utilize db indexing
            "AND (:#{#filter.medicineType} IS NULL OR med.medicineType = :#{#filter.medicineType}) " +
            "AND (:#{#filter.minRating} IS NULL OR med.rating >= :#{#filter.minRating}) " +
            "AND (:#{#filter.maxRating} IS NULL OR med.rating <= :#{#filter.maxRating}) " +
            "ORDER BY med.name ")
    List<Medicine> search(@Param("filter") MedicineSearchDTO medicineSearchDTO, Pageable pageable);
}
