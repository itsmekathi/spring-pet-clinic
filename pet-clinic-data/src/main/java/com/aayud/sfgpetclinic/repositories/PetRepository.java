package com.aayud.sfgpetclinic.repositories;

import com.aayud.sfgpetclinic.models.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
