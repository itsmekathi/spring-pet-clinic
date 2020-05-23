package com.aayud.sfgpetclinic.bootstrap;

import com.aayud.sfgpetclinic.models.Owner;
import com.aayud.sfgpetclinic.models.Pet;
import com.aayud.sfgpetclinic.models.PetType;
import com.aayud.sfgpetclinic.models.Vet;
import com.aayud.sfgpetclinic.services.OwnerService;
import com.aayud.sfgpetclinic.services.PetService;
import com.aayud.sfgpetclinic.services.PetTypeService;
import com.aayud.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private  final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType =  petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType =  petTypeService.save(cat);
        System.out.println("Loaded Pet Types");

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
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("John");
        vet2.setLastName("Smith");
        vetService.save(vet2);

        System.out.println("Loaded Vets.....");
    }
}
