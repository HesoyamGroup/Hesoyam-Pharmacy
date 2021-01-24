package com.hesoyam.pharmacy.user.service;

import com.hesoyam.pharmacy.user.model.Role;

import java.util.Collection;

public interface IRoleService {
    Collection<Role> findById(Long id);
    Collection<Role> findByName(String name);
}
