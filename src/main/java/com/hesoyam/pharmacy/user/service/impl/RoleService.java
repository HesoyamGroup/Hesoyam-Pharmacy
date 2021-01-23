package com.hesoyam.pharmacy.user.service.impl;

import com.hesoyam.pharmacy.user.model.Role;
import com.hesoyam.pharmacy.user.repository.RoleRepository;
import com.hesoyam.pharmacy.user.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class RoleService implements IRoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Collection<Role> findById(Long id) {
        Role role = roleRepository.getOne(id);
        Collection<Role> roles = new ArrayList<>();
        if(role != null) roles.add(role);

        return roles;
    }

    @Override
    public Collection<Role> findByName(String name) {
        Role role = roleRepository.findByName(name);
        Collection<Role> roles = new ArrayList<>();
        if(role!=null) roles.add(role);

        return roles;
    }
}
