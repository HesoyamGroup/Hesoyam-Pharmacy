package com.hesoyam.pharmacy.pharmacy.service.impl;

import com.hesoyam.pharmacy.pharmacy.dto.PharmacyAdministratorCreateDTO;
import com.hesoyam.pharmacy.pharmacy.dto.PharmacyCreateDTO;
import com.hesoyam.pharmacy.pharmacy.exceptions.InvalidPharmacyCreateRequest;
import com.hesoyam.pharmacy.pharmacy.model.Inventory;
import com.hesoyam.pharmacy.pharmacy.model.Pharmacy;
import com.hesoyam.pharmacy.pharmacy.repository.PharmacyRepository;
import com.hesoyam.pharmacy.pharmacy.service.IInventoryService;
import com.hesoyam.pharmacy.pharmacy.service.IPharmacyService;
import com.hesoyam.pharmacy.user.model.Administrator;
import com.hesoyam.pharmacy.user.model.Role;
import com.hesoyam.pharmacy.user.model.RoleEnum;
import com.hesoyam.pharmacy.user.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class PharmacyService implements IPharmacyService {

    @Autowired
    private PharmacyRepository pharmacyRepository;

    @Autowired
    private IInventoryService inventoryService;

    @Autowired
    private IRoleService roleService;

    @Override
    public Pharmacy create(PharmacyCreateDTO pharmacyCreateDTO) throws InvalidPharmacyCreateRequest {
        //Leave default transaction isolation here (SERIALIZABLE).
        //We want to make sure all of pharmacy related entities can be successfully created.
        Pharmacy pharmacy = new Pharmacy();
        pharmacy.setName(pharmacyCreateDTO.getName());
        pharmacy.setAddress(pharmacyCreateDTO.getAddress());
        pharmacy.setDescription(pharmacyCreateDTO.getDescription());
        pharmacy.setRating(0);
        pharmacy.setAdministrator(getAdministratorsFromPharmacyCreateDTO(pharmacyCreateDTO));
        Pharmacy savedPharmacy;

        try{
            savedPharmacy = pharmacyRepository.save(pharmacy);
            pharmacy.setInventory(createPharmacyInventory(pharmacy));
            savedPharmacy = pharmacyRepository.save(savedPharmacy);
        }catch (DataIntegrityViolationException exception){
            throw new InvalidPharmacyCreateRequest("Database constraint violation.");
        }

        return savedPharmacy;
    }

    private Inventory createPharmacyInventory(Pharmacy pharmacy){
        Inventory inventory = new Inventory();
        inventory.setPharmacy(pharmacy);
        return inventoryService.create(inventory);
    }

    private List<Administrator> getAdministratorsFromPharmacyCreateDTO(PharmacyCreateDTO pharmacyCreateDTO){
        return pharmacyCreateDTO.getAdministrators().stream().map(adminDTO -> createAdministratorObjectFromDTO(adminDTO)).collect(Collectors.toList());
    }

    private Administrator createAdministratorObjectFromDTO(PharmacyAdministratorCreateDTO pharmacyAdministratorCreateDTO){
        Administrator administrator = new Administrator();
        administrator.setRoleEnum(RoleEnum.ADMINISTRATOR);
        administrator.setPasswordReset(false);
        administrator.setEnabled(true);
        administrator.setEmail(pharmacyAdministratorCreateDTO.getEmail());
        administrator.setPassword(UUID.randomUUID().toString().substring(0, 15));
        administrator.setFirstName(pharmacyAdministratorCreateDTO.getFirstName());
        administrator.setLastName(pharmacyAdministratorCreateDTO.getLastName());
        administrator.setGender(pharmacyAdministratorCreateDTO.getGender());
        administrator.setTelephone(pharmacyAdministratorCreateDTO.getTelephone());
        administrator.setAddress(pharmacyAdministratorCreateDTO.getAddress());
        administrator.setLastPasswordResetDate(new Timestamp((new Date().getTime())));

        List<Role> roles = (List<Role>) roleService.findByName("ROLE_ADMINISTRATOR");
        administrator.setAuthorities(roles);


        return administrator;
    }

    @Override
    public List<Pharmacy> getAll() {
        return pharmacyRepository.findAll();
    }

    @Override
    public Pharmacy getByAdministrator(Long id) {
        return pharmacyRepository.getPharmacyByAdministrator(id);
    }

    @Override
    public Pharmacy findOne(Long id) {
        return pharmacyRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Pharmacy> findAllPharmaciesByMedicine(Long id) {
        return pharmacyRepository.getPharmacyByMedicineAvailability(id);
    }
}
