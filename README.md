# Final-Assignment-Mini-Project-SCS3203

#Steps To Build And Run

1. Open a terminal and go to the project folder
2. Run "gradlew build -Dquarkus.package.type=uber-jar" to build the app
3. Run "java -jar build/petstore-runner.jar" to run the app

#Steps To Test App

1. Add a pet
	
	url : http://localhost:8080/v1/pets
	
	method : POST
	
	body : {
		    "petName" : "boola",
		    "petType" : "dog",
		    "petAge" : 3
		}

2. Get all pets

	url : http://localhost:8080/v1/pets
	
	method : GET

3. Get a pet by ID

	url : http://localhost:8080/v1/pets/1
	
	method : GET

4. Delete a pet

	url : http://localhost:8080/v1/pets/1
	
	method : DELETE
	
5. Update a pet

	url : http://localhost:8080/v1/pets
	
	method : PUT

	body : {
		    "petId": 1,
		    "petName" : "boooooola",
		    "petType" : "dog",
		    "petAge" : 3
		}

6. Add a pet type

	url : http://localhost:8080/v1/pets/pettypes
	
	method : POST

	body : {
		    "petType" : "dog"
		}

7. Get all pet types

	url : http://localhost:8080/v1/pets/pettypes
	
	method : GET

8. Get a pet type by ID

	url : http://localhost:8080/v1/pets/pettypes/1
	
	method : GET

9. Update a pet type

	url : http://localhost:8080/v1/pets/pettypes
	
	method : PUT
	
	body : {
		    "petType" : "black dog",
		    "petTypeId": 1
		}

10. Delete a pet type

	url : http://localhost:8080/v1/pets/pettypes/1
	
	method : DELETE
