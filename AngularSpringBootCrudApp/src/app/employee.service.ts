import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Employee } from './employee';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private baseURL = "http://localhost:8084/api/v1/employee";
  constructor( private http: HttpClient) { }

  getEmployeesList():Observable<Employee[]>
  {
    return this.http.get<Employee[]>(this.baseURL);
  }

  createEmployee(employee: Employee):Observable<Employee[]>
  {
    return this.http.post<Employee[]>(this.baseURL,employee);
  }

  getEmployeeByID(id:number):Observable<Employee>
  {
    return this.http.get<Employee>(this.baseURL+"/"+id);
  }

  updateEmployee(id: number, employee: Employee):Observable<Employee[]>
  {
    return this.http.put<Employee[]>(this.baseURL+"/"+id,employee);
  }

  deleteEmployeeByID(id:number):Observable<Employee[]>
  {
    return this.http.delete<Employee[]>(this.baseURL+"/"+id);
  }
}
