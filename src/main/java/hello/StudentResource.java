package hello;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

@Relation(collectionRelation="students")
public class StudentResource extends ResourceSupport {

	private Long studentId;
	private String firstName;
	private String lastName;

	public StudentResource(){
		
	}
	
	public StudentResource(String firstName, String lastName, Long studentId) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.studentId = studentId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Long getStudentId() {
		return studentId;
	}

	
}
