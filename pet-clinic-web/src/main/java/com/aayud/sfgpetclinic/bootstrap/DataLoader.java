package com.aayud.sfgpetclinic.bootstrap;

import com.aayud.sfgpetclinic.models.*;
import com.aayud.sfgpetclinic.services.OwnerService;
import com.aayud.sfgpetclinic.services.PetTypeService;
import com.aayud.sfgpetclinic.services.SpecialityService;
import com.aayud.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private  final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();
        if( count == 0 ) {
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType =  petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType =  petTypeService.save(cat);
        System.out.println("Loaded Pet Types");

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadioloty =  specialityService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSurgery = specialityService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");
        Speciality savedDentistry = specialityService.save(dentistry);

        Owner owner1 = new Owner();
        owner1.setFirstName("Kathiravan");
        owner1.setLastName("Balasubramanian");
        ownerService.save(owner1);

        Pet kathiPet = new Pet();
        kathiPet.setPetType(savedDogPetType);
        kathiPet.setOwner(owner1);
        kathiPet.setBirthDate(LocalDate.now());
        kathiPet.setName("JhuJu");
        owner1.getPets().add(kathiPet);


        Owner owner2 = new Owner();
        owner2.setFirstName("Thiruveni");
        owner2.setLastName("Balasubramanian");
        ownerService.save(owner2);

        Pet veniPet = new Pet();
        veniPet.setPetType(savedCatPetType);
        veniPet.setBirthDate(LocalDate.now());
        veniPet.setName("Tom");
        veniPet.setOwner(owner2);
        owner2.getPets().add(veniPet);


        System.out.println("Loaded Owners....");

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vet1.getSpecialities().add(savedRadioloty);
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("John");
        vet2.setLastName("Smith");
        vet2.getSpecialities().add(savedSurgery);
        vetService.save(vet2);

        System.out.println("Loaded Vets.....");
    }
}
