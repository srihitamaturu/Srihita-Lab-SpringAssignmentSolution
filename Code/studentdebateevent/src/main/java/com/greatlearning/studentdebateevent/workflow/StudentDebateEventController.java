package com.greatlearning.studentdebateevent.workflow;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.greatlearning.studentdebateevent.domain.entity.Student;
import com.greatlearning.studentdebateevent.service.StudentDataService;

@Controller
@RequestMapping("/student")
public class StudentDebateEventController {

	@Autowired
	private StudentDataService studentDataService;

	// add mapping for "/list"

	@RequestMapping("/list")
	public String listStudents(Model theModel) {

		// get Books from db
		List<Student> theStudents = studentDataService.findAll();

		// add to the spring model
		theModel.addAttribute("Students", theStudents);

		return "StudentList";
	}

	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		// create model attribute to bind form data
		Student theStudent = new Student();

		theModel.addAttribute("Student", theStudent);

		return "StudentEnrolment";
	}

	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("studentId") int theId, Model theModel) {

		// get the Book from the service
		Student theStudent = studentDataService.findById(theId);

		// set Book as a model attribute to pre-populate the form
		theModel.addAttribute("Student", theStudent);

		// send over to our form
		return "StudentEnrolment";
	}

	@PostMapping("/save")
	public String saveBook(@RequestParam("id") int id, @RequestParam("name") String name,
			@RequestParam("department") String department, @RequestParam("country") String country) {

//		int id = 0;
//		if(!StringUtils.hasLength(theId)) {
//			id = Integer.parseInt(theId);
//		}
		
		Student theStudent;
		
		if (id != 0) {
			theStudent = studentDataService.findById(id);
			theStudent.setName(name);
			theStudent.setDepartment(department);
			theStudent.setCountry(country);
		} else
			theStudent = new Student(name, department, country);
		// save the Book
		studentDataService.save(theStudent);

		// use a redirect to prevent duplicate submissions
		return "redirect:/student/list";

	}

	@RequestMapping("/delete")
	public String delete(@RequestParam("studentId") int theId) {

		// delete the Book
		studentDataService.deleteById(theId);

		// redirect to /Books/list
		return "redirect:/student/list";

	}

	@RequestMapping(value = "/403")
	public ModelAndView accesssDenied(Principal user) {

		ModelAndView model = new ModelAndView();

		if (user != null) {
			model.addObject("msg", "Hi " + user.getName() + ", you do not have permission to access this page!");
		} else {
			model.addObject("msg", "You do not have permission to access this page!");
		}

		model.setViewName("403");
		return model;

	}

}