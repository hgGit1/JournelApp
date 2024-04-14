package net.engineeringdigest.journelApp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import net.engineeringdigest.journelApp.entity.JournelEntry;

public interface JournelEntryRepository extends MongoRepository<JournelEntry, ObjectId> {

}
