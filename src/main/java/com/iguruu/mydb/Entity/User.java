package com.iguruu.mydb.Entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
@Entity
public class User {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String fullName;
	
	@Column(nullable = false, unique = true)
	private String userName;
	
	@Column (nullable = false, unique = true)
	private String password;
	
	@Column (nullable = false)
	private String email;
	
	 @ManyToMany(fetch = FetchType.EAGER)
	 @JoinTable(
	        name = "User_Roles",
	        joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
	        inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id")
	    )
	    private Set<Role> roles;
	 
	 

}
