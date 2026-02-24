import { ChangeDetectorRef, Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { NutritionService } from '../../../../../services/nutrition-service';

@Component({
  selector: 'app-consume-meal',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule
  ],
  templateUrl: './consume-meal.html',
  styleUrls: ['./consume-meal.scss']
})
export class ConsumeMeal {

  cardId: number | null = null;
  mealType: 'BREAKFAST' | 'LUNCH' | 'DINNER' = 'BREAKFAST';

  isLoading = false;
  message: string | null = null;
  error: string | null = null;

  constructor(
    private nutritionService: NutritionService,
    private cdr: ChangeDetectorRef
  ) {}

  consumeMeal(): void {
    if (!this.cardId) {
      this.error = 'Card ID is required';
      return;
    }

    this.isLoading = true;
    this.message = null;
    this.error = null;

    this.nutritionService.consumeMeal(this.cardId, this.mealType)
      .subscribe({
        next: () => {
          this.message = 'Meal successfully consumed';
          this.isLoading = false;
          this.cdr.detectChanges();
        },
        error: (err) => {
          this.isLoading = false;
          if (err.status === 400) {
            this.error = 'No remaining meals of this type';
          } else if (err.status === 404) {
            this.error = 'Card not found';
          } else if (err.status === 403) {
            this.error = 'Not authorized';
          } else {
            this.error = 'Unexpected error occurred';
          }
          this.cdr.detectChanges();
        }
    });
  }
}