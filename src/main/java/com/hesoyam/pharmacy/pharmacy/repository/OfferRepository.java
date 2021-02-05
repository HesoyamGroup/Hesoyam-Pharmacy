package com.hesoyam.pharmacy.pharmacy.repository;

import com.hesoyam.pharmacy.pharmacy.dto.OfferFilterCriteria;
import com.hesoyam.pharmacy.pharmacy.model.Offer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Long> {
    List<Offer> getOffersBySupplier_Id(Long id, Pageable pageable);
    Offer getOfferByIdAndSupplier_Id(Long offerId, Long supplierId);
    @Query("SELECT offer FROM Offer offer WHERE " +
            "offer.totalPrice >= :#{#filter.minPrice} " +
            "AND offer.totalPrice <= :#{#filter.maxPrice} " +
            "AND offer.supplier.id = :userId " +
            "AND (:#{#filter.offerStatus} = offer.offerStatus OR :#{#filter.offerStatus} IS NULL) " +
            "ORDER BY offer.id")
    List<Offer> getSupplierOffersFiltered(@Param("filter")OfferFilterCriteria offerFilterCriteria, @Param("userId") Long id, Pageable pageable);
}
