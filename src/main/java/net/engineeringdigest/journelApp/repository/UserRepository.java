package net.engineeringdigest.journelApp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import net.engineeringdigest.journelApp.entity.JournelEntry;
import net.engineeringdigest.journelApp.entity.User;

public interface UserRepository extends MongoRepository<User, ObjectId> {
	
	User findByUserName(String username);

	void deleteByUserName(String name);

}
