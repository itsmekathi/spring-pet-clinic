package com.aayud.sfgpetclinic.services;

import com.aayud.sfgpetclinic.models.Owner;

public interface OwnerService extends CrudService<Owner, Long> {
    Owner findByLastName(String lastName);
}
