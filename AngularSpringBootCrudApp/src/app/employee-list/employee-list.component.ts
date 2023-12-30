import { Component, OnInit } from '@angular/core';
import { Employee } from '../employee';
import { EmployeeService } from '../employee.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit {

  employee: Employee[] = [];

  constructor(private service: EmployeeService,private route: Router)
  {

  }

  ngOnInit(): void {
    this.getEmployees();
  }

  getEmployees()
  {
    this.service.getEmployeesList().subscribe(
      data => {this.employee = data},
      error => {console.log(error)}
    );
  }

  employeeDetails(id: number)
  {
    this.route.navigate(['employee-details',id]);
  }

  updateEmployee(id: number)
  {
    this.route.navigate(['update-employee',id]);
  }

  deleteEmployee(id:number)
  {
    this.service.deleteEmployeeByID(id).subscribe(data => {
      console.log("Record Deleted");
         this.getEmployees();
    });
  }
}
