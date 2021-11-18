package com.example.petstore;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@Path("/v1/pets")
@Produces("application/json")
public class PetResource {
	List<Pet> pets = new ArrayList<Pet>();
	List<PetType> petTypes = new ArrayList<PetType>();
	

	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "All Pets", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))) })
	@GET
	@Path("/")
	public Response getPets() {
		return Response.ok(pets).build();
	}

	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Pet for id", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "No Pet found for the id.") })
	@GET
	@Path("/{petId}")
	public Response getPet(@PathParam("petId") int petId) {
		boolean flag = false;
		for(int i = 0; i < pets.size() ; i++) {
			if(pets.get(i).getPetId()==petId) {
				flag = true;
			}
		}
		Pet pet = null;
		if (!flag) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}else {
			for(int i = 0; i < pets.size() ; i++) {
				if(pets.get(i).getPetId()==petId) {
					pet = pets.get(i);
				}
			}
			return Response.ok(pet).build();
						
		}
		
	}
	
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Add Pet", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))) })
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Response createPets(Pet pet) {
		int indx = this.pets.size();
		int lastId = 0;
		if(indx == 0) {
			lastId = 1;
		}else {
			lastId = pets.get(indx-1).getPetId()+1;
		}
		Pet pet1 = pet;
		pet1.setPetId(lastId);
		this.pets.add(pet1);
		return Response.ok(pet).build();
	}
	
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Delete Pet", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))) })
	@DELETE
	@Path("/{petId}")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Response deletePets(@PathParam("petId") int petId) {
		if (petId < 0 || pets.get(petId).getPetName() == "undefined") {
			return Response.status(Status.NOT_FOUND).build();
		}
		Pet pet = null;
		for(int i = 0; i < pets.size() ; i++) {
			if(pets.get(i).getPetId()==petId) {
				pet = pets.get(i);
				pets.remove(i);
			}
		}

		return Response.ok(pet).build();
	}
	
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Update Pet", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))) })
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Response updatePets(Pet pet) {
		if (pet.getPetId() < 0 ) {
			return Response.status(Status.NOT_FOUND).build();
		}
		Pet pet2 = null;
		for(int i = 0; i < pets.size() ; i++) {
			if(pets.get(i).getPetId()==pet.getPetId()) {
				pet2 = pets.get(i);
			}
		}
		pet2.setPetAge(pet.getPetAge());
		pet2.setPetName(pet.getPetName());
		pet2.setPetType(pet.getPetType());
		
		return Response.ok(pet2).build();
	}
	
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "All Pet Types", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "PetType"))) })
	@GET
	@Path("/pettypes/")
	public Response getPetTypes() {
		return Response.ok(petTypes).build();
	}

	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Pet Type for id", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "PetType"))),
			@APIResponse(responseCode = "404", description = "No Pet Type found for the id.") })
	@GET
	@Path("/pettypes/{petTypeId}")
	public Response getPetType(@PathParam("petTypeId") int petTypeId) {
		boolean flag = false;
		for(int i = 0; i < petTypes.size() ; i++) {
			if(petTypes.get(i).getPetTypeId()==petTypeId) {
				flag = true;
			}
		}
		PetType petType = null;
		if (!flag) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}else {
			for(int i = 0; i < petTypes.size() ; i++) {
				if(petTypes.get(i).getPetTypeId()==petTypeId) {
					petType = petTypes.get(i);
				}
			}
			return Response.ok(petType).build();
						
		}
		
	}
	
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Add Pet Type", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "PetType"))) })
	@POST
	@Path("/pettypes/")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Response createPetType(PetType petType) {
		int indx = this.petTypes.size();
		int lastId = 0;
		if(indx == 0) {
			lastId = 1;
		}else {
			lastId = petTypes.get(indx-1).getPetTypeId()+1;
		}
		PetType pet1 = petType;
		pet1.setPetTypeId(lastId);
		this.petTypes.add(pet1);
		return Response.ok(pet1).build();
	}
	
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Delete Pet Type", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "PetType"))) })
	@DELETE
	@Path("/pettypes/{petTypeId}")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Response deletePetType(@PathParam("petTypeId") int petTypeId) {
		if (petTypeId < 0 || petTypes.get(petTypeId).getPetType() == "undefined") {
			return Response.status(Status.NOT_FOUND).build();
		}
		PetType pet = null;
		for(int i = 0; i < petTypes.size() ; i++) {
			if(petTypes.get(i).getPetTypeId()==petTypeId) {
				pet = petTypes.get(i);
				petTypes.remove(i);
			}
		}

		return Response.ok(pet).build();
	}
	
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Update Pet Type", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "PetType"))) })
	@PUT
	@Path("/pettypes/")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Response updatePets(PetType pet) {
		if (pet.getPetTypeId() < 0 ) {
			return Response.status(Status.NOT_FOUND).build();
		}
		PetType pet2 = null;
		for(int i = 0; i < petTypes.size() ; i++) {
			if(petTypes.get(i).getPetTypeId()==pet.getPetTypeId()) {
				pet2 = petTypes.get(i);
			}
		}
		pet2.setPetType(pet.getPetType());
		
		return Response.ok(pet2).build();
	}
	
}
