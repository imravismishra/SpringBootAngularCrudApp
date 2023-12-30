package com.ravismishra99.SpringBootAngularCrudApp.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ravismishra99.SpringBootAngularCrudApp.entity.Employee;
import com.ravismishra99.SpringBootAngularCrudApp.repository.EmployeeRepository;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200/")
public class MainController {
	
	@Autowired
	private EmployeeRepository repository;

	@GetMapping("/employee")
	public List<Employee> getAllEmployees()
	{
		return repository.findAll();
	}
	
	@PostMapping("/employee")
	public Employee createEmployee(@RequestBody Employee employee)
	{
		return repository.save(employee);
	}
	
	@PutMapping("/employee/{id}")
	public Employee updateEmployee(@PathVariable int id, @RequestBody Employee employee) throws Exception{
		Employee emp = repository.findById(id)
				.orElseThrow(() -> new Exception("Employee not exist with id :" + id));
		
		emp.setName(employee.getName());
		emp.setEmail(employee.getEmail());
		emp.setProfile(employee.getProfile());
		
		Employee updatedEmployee = repository.save(emp);
		return updatedEmployee;
	}
	
	  @PostMapping("/upload")
	  public String handleFileUploadForm(@RequestPart("file") MultipartFile file) throws IOException {
	      
		 File f = new File("C:\\Users\\Admin\\Documents\\Java Projects\\SpringBootAngularCrudApp\\src\\main\\resources\\static\\uploads");
	      final Path path = Paths.get(f.getAbsolutePath() + File.separator + "static" + File.separator + "image");

	      if (!Files.exists(path)) {
	        Files.createDirectories(path);
	      }

	      Path filePath = path.resolve(file.getOriginalFilename());
	      Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

	      String fileUri = ServletUriComponentsBuilder.fromCurrentContextPath()
	          .path("/image/")
	          .path(file.getOriginalFilename())
	          .toUriString();

	      var result = Map.of(
	          "filename", file.getOriginalFilename(),
	          "fileUri", fileUri
	      );
	      return result.toString();

	  
	  }
	@GetMapping("/employee/{id}")
	public Employee getEmployeebyID(@PathVariable int id)
	{
		return repository.findById(id).orElse(new Employee());
	}
	
	@DeleteMapping("/employee/{id}")
	public Employee deleteEmployeebyID(@PathVariable int id)
	{
		Employee emp = repository.findById(id).orElse(null);
		if(emp!=null)
		{
			repository.deleteById(id);
		}
		return emp;
	}
	
	
	
	
}
