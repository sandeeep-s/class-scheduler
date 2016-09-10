package hello;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class StudentResourceAssembler extends ResourceAssemblerSupport<Student, StudentResource> {

	public StudentResourceAssembler() {
		super(StudentController.class, StudentResource.class);
	}

	@Override
	public StudentResource toResource(Student student) {
		StudentResource studentResource = createResourceWithId(student.getId(), student);
		studentResource.add(linkTo(
				methodOn(StudentController.class).update(studentResource, studentResource.getStudentId())).withRel(
				"edit"));
		studentResource.add(linkTo(methodOn(StudentController.class).delete(student.getId())).withRel("delete"));
		return studentResource;
	}

	@Override
	protected StudentResource instantiateResource(Student student) {

		return new StudentResource(student.getFirstName(), student.getLastName(), student.getId());

	}
}
