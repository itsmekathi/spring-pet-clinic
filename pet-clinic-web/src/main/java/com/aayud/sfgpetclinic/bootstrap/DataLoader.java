package com.aayud.sfgpetclinic.bootstrap;

import com.aayud.sfgpetclinic.models.*;
import com.aayud.sfgpetclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private  final OwnerService ownerService;
    private final VetService vetService;
    private final PetService petService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetService petService, PetTypeService petTypeService, SpecialityService specialityService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petService = petService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
        this.visitService = visitService;
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
        Owner savedKathiOwner = ownerService.save(owner1);

        Pet jhuJhu = new Pet();
        jhuJhu.setPetType(savedDogPetType);
        jhuJhu.setOwner(owner1);
        jhuJhu.setBirthDate(LocalDate.now());
        jhuJhu.setName("JhuJu");
        owner1.getPets().add(jhuJhu);
        jhuJhu.setOwner(savedKathiOwner);
        Pet savedJhuJhu = petService.save(jhuJhu);


        Owner owner2 = new Owner();
        owner2.setFirstName("Thiruveni");
        owner2.setLastName("Balasubramanian");
        Owner savedVeniOwner = ownerService.save(owner2);

        Pet veniPet = new Pet();
        veniPet.setPetType(savedCatPetType);
        veniPet.setBirthDate(LocalDate.now());
        veniPet.setName("Tom");
        veniPet.setOwner(savedVeniOwner);
        savedVeniOwner.getPets().add(veniPet);
        Pet savedVeniPet = petService.save(veniPet);

        Visit jhujhuVisit = new Visit();
        jhujhuVisit.setDescription("JhuJhu not having food");
        jhujhuVisit.setDate(LocalDate.now());
        jhujhuVisit.setPet(jhuJhu);

        visitService.save(jhujhuVisit);

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
