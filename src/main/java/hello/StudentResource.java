package hello;

import org.springframework.hateoas.ResourceSupport;

public class StudentResource extends ResourceSupport {

	private String firstName;
	private String lastName;

	public StudentResource(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

}
