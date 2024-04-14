package net.engineeringdigest.journelApp.entity;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

@Document(collection = "users")
public class User {
	
	@Id
	private ObjectId id;
	
	@Indexed(unique = true)
	@NonNull
	private String userName;
	
	@NonNull
	private String password;
	
	@DBRef
	private List<JournelEntry> journalEntries = new ArrayList<>();
	
	private List<String> roles;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<JournelEntry> getJournalEntries() {
		return journalEntries;
	}

	public void setJournalEntries(List<JournelEntry> journalEntries) {
		this.journalEntries = journalEntries;
	}
	
	

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

//	public User(ObjectId id, String userName, String password, List<JournelEntry> journalEntries) {
//		super();
//		this.id = id;
//		this.userName = userName;
//		this.password = password;
//		this.journalEntries = journalEntries;
//	}
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

public User(ObjectId id, String userName, String password, List<JournelEntry> journalEntries, List<String> roles) {
	super();
	this.id = id;
	this.userName = userName;
	this.password = password;
	this.journalEntries = journalEntries;
	this.roles = roles;
}

public static class Builder {
    private String userName;
    private String password;
    private ArrayList<String> roles;
    
    public Builder userName(String userName) {
        this.userName = userName;
        return this;
    }
    
    public Builder password(String password) {
        this.password = password;
        return this;
    }
    
    public Builder roles(ArrayList<String> roles) {
        this.roles = roles;
        return this;
    }
    
    
    public User build() {
    	User user = new User();
    	user.userName = this.userName;
    	user.password = this.password;
    	user.roles = this.roles;
        return user;
    }
	

}
}

//Builder class

