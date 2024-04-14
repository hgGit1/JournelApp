package net.engineeringdigest.journelApp.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import net.engineeringdigest.journelApp.entity.JournelEntry;
import net.engineeringdigest.journelApp.entity.User;
import net.engineeringdigest.journelApp.repository.JournelEntryRepository;

@Component
public class JournelEntryService {
	
	
	@Autowired
	private JournelEntryRepository journelEntryReposotory;
	
	@Autowired
	private UserService userService;
	
	private static final Logger log = LoggerFactory.getLogger(JournelEntryService.class);

	@Transactional
	public void saveEntry(JournelEntry myEntry, String userName) {
		try {
			User user = userService.findByUserName(userName);
			myEntry.setDate(LocalDateTime.now());
			JournelEntry saved = journelEntryReposotory.save(myEntry);
			user.getJournalEntries().add(saved);
			userService.saveUser(user);
		} catch(Exception e) {
			log.info("throwing error in saving Journer Entry");
			log.error("error here", e);
			throw new RuntimeException("An error occured while saving the entry "+e);
		}
		
	}
	
	public void saveEntry(JournelEntry myEntry) {
		journelEntryReposotory.save(myEntry);
	}
	
	public List<JournelEntry> getAll(){
		return journelEntryReposotory.findAll();
	}
	
	public Optional<JournelEntry> findById(ObjectId id) {
		return journelEntryReposotory.findById(id);
		
	}
	
	@Transactional
	public void deleteJournelById(ObjectId id, String userName) {
		try {
			User user = userService.findByUserName(userName);
			boolean removedUser = user.getJournalEntries().removeIf(x-> x.getId().equals(id));
			if(removedUser) {
				userService.saveUser(user);
				journelEntryReposotory.deleteById(id);
			}
		} catch(Exception e){
			System.out.println("getting exception in deleting Journel: "+ e);
			throw new RuntimeException("An error occured in removing journel", e);
			
		}
		}

}
