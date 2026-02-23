import { P } from '@angular/cdk/keycodes';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { withExperimentalPlatformNavigation } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UsersService {
  
  private baseUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {}

  getAllEmployees(): Observable<any> {
    const token = localStorage.getItem('token');

    const headers = {
      Authorization: `Bearer ${token}`
    };

    return this.http.get(`${this.baseUrl}/employees`, { headers });
  }

  getEmployeeById(employeeId: any): Observable<any> {
    const token = localStorage.getItem('token');

    const headers = {
      Authorization: `Bearer ${token}`
    };

    return this.http.get(`${this.baseUrl}/employees/` + employeeId, { headers });
  }

  deleteEmployee(employeeId: any): Observable<any> {
    const token = localStorage.getItem('token');

    const headers = {
      Authorization: `Bearer ${token}`
    };

    return this.http.delete(`${this.baseUrl}/employees/` + employeeId, { headers });
  }

  getAllStudents(): Observable<any> {
    const token = localStorage.getItem('token');

    const headers = {
      Authorization: `Bearer ${token}`
    };

    return this.http.get(`${this.baseUrl}/students`, { headers });
  }

  getStudentById(studentId: any): Observable<any> {
    const token = localStorage.getItem('token');

    const headers = {
      Authorization: `Bearer ${token}`
    };

    return this.http.get(`${this.baseUrl}/students/` + studentId, { headers });
  }

  deleteStudents(employeeId: any): Observable<any> {
    const token = localStorage.getItem('token');

    const headers = {
      Authorization: `Bearer ${token}`
    };

    return this.http.delete(`${this.baseUrl}/students/` + employeeId, { headers });
  }

}
