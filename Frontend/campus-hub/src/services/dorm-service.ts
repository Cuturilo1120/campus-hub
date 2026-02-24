import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class DormService {

  private baseUrl = 'http://localhost:8081/api';

  constructor(private http: HttpClient) {}

  getAllDorms(): Observable<any> {
    const token = localStorage.getItem('token');
    const headers = {
      Authorization: `Bearer ${token}`
    };
    return this.http.get(`${this.baseUrl}/dorms`, { headers });
  }

  getDormById(dormId: number): Observable<any> {
    const token = localStorage.getItem('token');
    const headers = {
      Authorization: `Bearer ${token}`
    };
    return this.http.get(`${this.baseUrl}/dorms/${dormId}`, { headers });
  }

  getDormCapacity(dormId: number): Observable<any> {
    const token = localStorage.getItem('token');
    const headers = {
      Authorization: `Bearer ${token}`
    };
    return this.http.get(`${this.baseUrl}/dorms/${dormId}/capacity`, { headers });
  }

  getAllPavilions(): Observable<any> {
    const token = localStorage.getItem('token');
    const headers = {
      Authorization: `Bearer ${token}`
    };
    return this.http.get(`${this.baseUrl}/pavilions`, { headers });
  }

  getPavilionById(pavilionId: number): Observable<any> {
    const token = localStorage.getItem('token');
    const headers = {
      Authorization: `Bearer ${token}`
    };
    return this.http.get(`${this.baseUrl}/pavilions/${pavilionId}`, { headers });
  }

  getAllRooms(): Observable<any> {
    const token = localStorage.getItem('token');
    const headers = {
      Authorization: `Bearer ${token}`
    };
    return this.http.get(`${this.baseUrl}/rooms`, { headers });
  }

  getRoomById(roomId: number): Observable<any> {
    const token = localStorage.getItem('token');
    const headers = {
      Authorization: `Bearer ${token}`
    };
    return this.http.get(`${this.baseUrl}/rooms/${roomId}`, { headers });
  }

  getAllRoomApplications(): Observable<any> {
    const token = localStorage.getItem('token');
    const headers = {
      Authorization: `Bearer ${token}`
    };
    return this.http.get(`${this.baseUrl}/roomApplications`, { headers });
  }

  getRoomApplicationById(id: number): Observable<any> {
    const token = localStorage.getItem('token');
    const headers = {
      Authorization: `Bearer ${token}`
    };
    return this.http.get(`${this.baseUrl}/roomApplications/${id}`, { headers });
  }

  getAllDormStays(): Observable<any> {
    const token = localStorage.getItem('token');
    const headers = {
      Authorization: `Bearer ${token}`
    };
    return this.http.get(`${this.baseUrl}/dormStays`, { headers });
  }

  getDormStayById(id: number): Observable<any> {
    const token = localStorage.getItem('token');
    const headers = {
      Authorization: `Bearer ${token}`
    };
    return this.http.get(`${this.baseUrl}/dormStays/${id}`, { headers });
  }
}
