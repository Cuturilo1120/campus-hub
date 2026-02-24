import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class DormService {

  private baseUrl = 'http://localhost:8081/api';

  constructor(private http: HttpClient) {}

  createDorm(name: string, city: string): Observable<any> {
    const token = localStorage.getItem('token');
    const headers = {
      Authorization: `Bearer ${token}`
    };
    return this.http.post(`${this.baseUrl}/dorms`, { name, city }, { headers });
  }

  createPavilion(number: number, address: string, dormId: number): Observable<any> {
    const token = localStorage.getItem('token');
    const headers = {
      Authorization: `Bearer ${token}`
    };
    return this.http.post(`${this.baseUrl}/pavilions`, { number, address, dorm: { id: dormId } }, { headers });
  }

  createRoom(roomNumber: string, capacity: number, pavilionId: number): Observable<any> {
    const token = localStorage.getItem('token');
    const headers = {
      Authorization: `Bearer ${token}`
    };
    return this.http.post(`${this.baseUrl}/rooms`, { roomNumber, capacity, pavilion: { id: pavilionId } }, { headers });
  }

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

  applyForRoom(dormId: number): Observable<any> {
    const token = localStorage.getItem('token');
    const headers = {
      Authorization: `Bearer ${token}`
    };
    return this.http.post(`${this.baseUrl}/roomApplications/apply`, { dormId }, { headers });
  }

  getMyRoomApplications(): Observable<any> {
    const token = localStorage.getItem('token');
    const headers = {
      Authorization: `Bearer ${token}`
    };
    return this.http.get(`${this.baseUrl}/roomApplications/mine`, { headers });
  }

  acceptRoomApplication(id: number): Observable<any> {
    const token = localStorage.getItem('token');
    const headers = {
      Authorization: `Bearer ${token}`
    };
    return this.http.patch(`${this.baseUrl}/roomApplications/${id}/accept`, {}, { headers });
  }

  rejectRoomApplication(id: number): Observable<any> {
    const token = localStorage.getItem('token');
    const headers = {
      Authorization: `Bearer ${token}`
    };
    return this.http.patch(`${this.baseUrl}/roomApplications/${id}/reject`, {}, { headers });
  }

  getMyRoomApplicationById(id: number): Observable<any> {
    const token = localStorage.getItem('token');
    const headers = {
      Authorization: `Bearer ${token}`
    };
    return this.http.get(`${this.baseUrl}/roomApplications/mine/${id}`, { headers });
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
