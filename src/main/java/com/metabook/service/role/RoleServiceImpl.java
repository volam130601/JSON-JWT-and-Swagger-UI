package com.metabook.service.role;

import com.metabook.entity.Role;
import com.metabook.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepo;

    @Override
    public Role findByCode(String code) {
        return roleRepo.findByCode(code);
    }
}
