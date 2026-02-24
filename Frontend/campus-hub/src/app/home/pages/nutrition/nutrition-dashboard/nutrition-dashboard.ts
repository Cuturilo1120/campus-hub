import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-nutrition-dashboard',
  standalone: true,
  imports: [
    RouterLink,
    CommonModule,
    MatCardModule,
    MatButtonModule
  ],
  templateUrl: './nutrition-dashboard.html',
  styleUrls: ['./nutrition-dashboard.scss']
})
export class NutritionDashboard {

  get role(): string | null {
    return localStorage.getItem('role');
  }
}