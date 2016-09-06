package hello;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

	@Autowired
	private StudentRepository repository;

	@RequestMapping("/student")
	public HttpEntity<StudentResource> student() {

		repository.save(new Student("Dipti", "Revankar"));

		StudentResource studentResource = new StudentResource("Sandeep", "Shinde");

		for (Student student : repository.findAll()) {

			studentResource = new StudentResource(student.getFirstName(), student.getLastName());

			studentResource.add(linkTo(methodOn(StudentController.class).student()).withSelfRel());

		}

		return new ResponseEntity<StudentResource>(studentResource, HttpStatus.OK);

	}

}
