package hello;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentRepository repository;

	@Autowired
	private StudentResourceAssembler studentResourceAssembler;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Resources<StudentResource>> listAll() {

		List<StudentResource> resourceList = new ArrayList<StudentResource>();

		for (Student student : repository.findAll()) {

			resourceList.add(studentResourceAssembler.toResource(student));
		}

		Resources<StudentResource> resources = new Resources<StudentResource>(resourceList, linkTo(
				methodOn(StudentController.class).listAll()).withSelfRel());
		resources.add(linkTo(methodOn(StudentController.class).create(new StudentResource())).withRel("create"));

		return new ResponseEntity<Resources<StudentResource>>(resources, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.POST)
	public HttpEntity<StudentResource> create(@RequestBody StudentResource studentResource) {

		Student student = new Student(studentResource.getFirstName(), studentResource.getLastName());
		student = repository.save(student);
		return new ResponseEntity<StudentResource>(studentResourceAssembler.toResource(student), HttpStatus.CREATED);

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public HttpEntity<StudentResource> getStudent(@PathVariable("id") Long id) {

		Student student = repository.findOne(id);
		return new ResponseEntity<StudentResource>(studentResourceAssembler.toResource(student), HttpStatus.OK);

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public HttpEntity<StudentResource> update(@RequestBody StudentResource studentResource, @PathVariable Long id) {

		Student student = repository.findOne(id);
		student.setFirstName(studentResource.getFirstName());
		student.setLastName(studentResource.getLastName());
		student = repository.save(student);
		return new ResponseEntity<StudentResource>(studentResourceAssembler.toResource(student), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public HttpEntity<StudentResource> delete(@PathVariable Long id) {
		repository.delete(id);
		return new ResponseEntity<StudentResource>(HttpStatus.NO_CONTENT);
	}

}
