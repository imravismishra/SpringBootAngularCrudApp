import { Component, OnInit } from '@angular/core';
import { Employee } from '../employee';
import { EmployeeService } from '../employee.service';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit {

  employee: Employee[] = [];

  constructor(private service: EmployeeService)
  {

  }

  ngOnInit(): void {
    this.getEmployees();
  }

  private getEmployees()
  {
    this.service.getEmployeesList().subscribe(
      data => {this.employee = data},
      error => {console.log(error)}
    );
  }
}
