import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { NutritionService } from '../../../../../services/nutrition-service';

@Component({
  selector: 'app-student-menu',
  standalone: true,
  imports: [
    CommonModule,
    MatCardModule,
    MatButtonModule
  ],
  templateUrl: './student-menu.html',
  styleUrls: ['./student-menu.scss']
})
export class StudentMenu implements OnInit {

  menu: any = null;
  isLoading = false;
  message: string | null = null;

  constructor(
    private nutritionService: NutritionService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.loadMenu();
  }

  loadMenu() {
    this.isLoading = true;

    this.nutritionService.getStudentMenu().subscribe({
      next: (data) => {
        this.menu = data;
        this.isLoading = false;
        this.cdr.detectChanges();
      },
      error: () => {
        this.menu = null;
        this.isLoading = false;
      }
    });
  }

  buyMeal(type: string) {
    this.message = null;

    this.nutritionService.buyMeal(type).subscribe({
      next: () => {
        this.message = `${type} purchased successfully`;
      },
      error: (err) => {
        if (err.status === 400) {
          this.message = 'Insufficient balance or invalid request';
        } else if (err.status === 403) {
          this.message = 'Only students can buy meals';
        } else {
          this.message = 'Purchase failed';
        }
      }
    });
  }


}