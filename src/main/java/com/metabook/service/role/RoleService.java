package com.metabook.service.role;

import com.metabook.entity.Role;

public interface RoleService {
    Role findByCode(String code);
}
