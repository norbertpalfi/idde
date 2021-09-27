# Usage
1. start docker
2. go to localhost:8080/cars.hbs
3. get redirected to /login

# JPA
use PROFILE: "jpa" in the docker-compose
Data can be added from the site, but due to an unknown bug 
(well, unknown solution for the bug), you have to run an update by hand in the SQL putty terminal

STEPS TO FOLLOW:
1. run docker compose
2. in a new terminal, enter this: docker exec -i UsedCars-db mysql -u UsedCars -pUsedCarsPassword
3. add Owners of choice from the setup.sql
4. add new entries from the site for cars
5. execute the following: UPDATE UsedCar SET owner_id = ownerId
6. everything should be fine! 

vizsga