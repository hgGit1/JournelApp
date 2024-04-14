package net.engineeringdigest.journelApp.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.engineeringdigest.journelApp.entity.JournelEntry;
import net.engineeringdigest.journelApp.entity.User;
import net.engineeringdigest.journelApp.service.JournelEntryService;
import net.engineeringdigest.journelApp.service.UserService;

@RestController
@RequestMapping("/journel")
public class JournelEntryController {
	
	@Autowired
	private JournelEntryService journelEntryService;
	
	@Autowired
	private UserService userService;
	

	
//	@GetMapping
//	public ResponseEntity<?> getAll(){
//		List<JournelEntry> allJournel = journelEntryService.getAll();
//		if(allJournel != null && !allJournel.isEmpty()) {
//			return new ResponseEntity<>(allJournel, HttpStatus.OK);
//		}
//		return new ResponseEntity<>(allJournel, HttpStatus.NO_CONTENT);
//	}
	
	//Code after adding user
	@GetMapping
	public ResponseEntity<?> getAllJournelEntriesOfUser(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		User user = userService.findByUserName(userName);
		
		List<JournelEntry> allJournel = user.getJournalEntries();
		if(allJournel != null && !allJournel.isEmpty()) {
			return new ResponseEntity<>(allJournel, HttpStatus.OK);
		}
		return new ResponseEntity<>(allJournel, HttpStatus.NO_CONTENT);
	}
	
//	@PostMapping
//	public ResponseEntity<JournelEntry> createEntry(@RequestBody JournelEntry myEntry) {
//		
//		try {
//			myEntry.setDate(LocalDateTime.now());
//			journelEntryService.saveEntry(myEntry);
//			return new ResponseEntity<>(myEntry,HttpStatus.CREATED);
//		} catch(Exception e){
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//		}
//	
//	}
	
	//Code after adding user
	@PostMapping
	public ResponseEntity<JournelEntry> createEntry(@RequestBody JournelEntry myEntry){
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		
		try {
			journelEntryService.saveEntry(myEntry, userName);
			return new ResponseEntity<>(myEntry,HttpStatus.CREATED);
		} catch(Exception e){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	
	}
	
	
	@GetMapping("/id/{findId}")
	public ResponseEntity<JournelEntry> getJournelEntryById(@PathVariable ObjectId findId) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		User user = userService.findByUserName(userName);
		List<JournelEntry> collect = user.getJournalEntries().stream().filter(x->x.getId().equals(findId)).collect(Collectors.toList());
		if(!collect.isEmpty() && collect!=null) {
			Optional<JournelEntry> journelEntry = journelEntryService.findById(findId);
			
			if(journelEntry.isPresent()) {
				return new ResponseEntity<>(journelEntry.get(),HttpStatus.OK);
			}			
		}
				
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		//return journelEntryService.findById(findId).orElse(null);	
	}
	
//	@DeleteMapping("/remove/{deleteId}")
//	public ResponseEntity<JournelEntry> deleteJournelEntryById(@PathVariable ObjectId deleteId) {
//		Optional<JournelEntry> entry = journelEntryService.findById(deleteId);
//		if(entry.isPresent()) {
//			journelEntryService.deleteJournelById(deleteId);
//			return new ResponseEntity<>(HttpStatus.ACCEPTED);
//		}
//		
//		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		
//	}
	
	//Code to after adding user
	@DeleteMapping("/remove/{deleteId}")
	public ResponseEntity<?> deleteJournelEntryById(@PathVariable ObjectId deleteId) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		try {
			journelEntryService.deleteJournelById(deleteId, userName);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
//	@PutMapping("/update/{id}")
//	public ResponseEntity<?> updateJournelById(@PathVariable ObjectId id, @RequestBody JournelEntry newEntry) {
//		JournelEntry oldEntry = journelEntryService.findById(id).orElse(null);
//		
//		if(oldEntry != null) {
//			oldEntry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle():oldEntry.getTitle());
//			oldEntry.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent():oldEntry.getContent());
//			//journelEntryService.saveEntry(oldEntry);
//			
//			return new ResponseEntity<>(oldEntry,HttpStatus.OK);
//		}
//		
//		return new ResponseEntity<>(oldEntry,HttpStatus.NOT_FOUND);
//	}
	
	//Code after using User
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateJournelById(@PathVariable ObjectId id, @RequestBody JournelEntry newEntry) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		User user = userService.findByUserName(userName);
		List<JournelEntry> collect = user.getJournalEntries().stream().filter(x->x.getId().equals(id)).collect(Collectors.toList());
		
		if(!collect.isEmpty()) {
			Optional<JournelEntry> journelEntry = journelEntryService.findById(id);
			if(journelEntry.isPresent()) {
				JournelEntry oldEntry = journelEntry.get();
				
				if(oldEntry != null) {
					oldEntry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle():oldEntry.getTitle());
					oldEntry.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent():oldEntry.getContent());
					journelEntryService.saveEntry(oldEntry);
					
					return new ResponseEntity<>(oldEntry,HttpStatus.OK);
				}
			}
		}
		
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
