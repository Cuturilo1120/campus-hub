import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class NutritionService {
  
  private baseUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {}

  createCard(studentId: number) {
    const token = localStorage.getItem('token');

    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`
    });

    return this.http.post(`${this.baseUrl}/cards`, { studentId }, { headers });
  }

  deleteCard(cardId: number) {
    const token = localStorage.getItem('token');

    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`
    });

    return this.http.delete(`${this.baseUrl}/cards/${cardId}`, { headers });
  }

  renewCard(cardId: number) {
    const token = localStorage.getItem('token');

    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`
    });   

    return this.http.patch(`${this.baseUrl}/cards/${cardId}/renew`, {}, { headers });
  }

  addFunds(cardId: number, amount: number) {
    const token = localStorage.getItem('token');

    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`
    });

    return this.http.patch(`${this.baseUrl}/cards/${cardId}/funds`, { amount }, { headers });
  }

  getCardByStudent(studentId: number) {
    const token = localStorage.getItem('token');

    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`
    });

    return this.http.get(`${this.baseUrl}/cards/student/${studentId}`, { headers });
  }

  getStudentMyCard() {
    const token = localStorage.getItem('token');

    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`
    });

    return this.http.get(`${this.baseUrl}/cards/mine`, { headers });
  }

  getStudentMenu() {
    const token = localStorage.getItem('token');

    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`
    });

    return this.http.get(`${this.baseUrl}/menu/mine`, { headers });
  }

  buyMeal(mealType: string) {
    const token = localStorage.getItem('token');

    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`
    });

    return this.http.post(`${this.baseUrl}/meals/buy`, { mealType } , { headers });
  }
  
}
