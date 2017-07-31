package hibernate4.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;


// or it shows "Unknown entity" error
@Entity
@Table(name="user")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Range(min=5, max=99)//@Length is for String, @Size is for List
	private Integer age;
	private String username;
	private String email;
	private String nationality;

	public User(Long id, Integer age, String username, String email, String nationality) {
		super();
		this.id = id;
		this.age = age;
		this.username = username;
		this.email = email;
		this.nationality = nationality;
	}
	public User(Integer age, String username, String email, String nationality) {
		this(null, age, username, email, nationality);
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", age=" + age + ", username=" + username + ", email=" + email + ", nationality="
				+ nationality + "]";
	}
	
	public User() {}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
