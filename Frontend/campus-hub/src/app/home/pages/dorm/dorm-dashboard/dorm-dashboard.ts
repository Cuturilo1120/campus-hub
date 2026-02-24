import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-dorm-dashboard',
  standalone: true,
  imports: [
    RouterLink,
    CommonModule,
    MatCardModule,
    MatButtonModule
  ],
  templateUrl: './dorm-dashboard.html',
  styleUrls: ['./dorm-dashboard.scss']
})
export class DormDashboard {

  get role(): string | null {
    return localStorage.getItem('role');
  }

}