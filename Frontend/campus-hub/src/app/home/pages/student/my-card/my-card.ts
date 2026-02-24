import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { NutritionService } from '../../../../../services/nutrition-service';

interface Meal {
  mealType: 'BREAKFAST' | 'LUNCH' | 'DINNER';
  amount: number;
}

interface CardResponse {
  id: number;
  balance: number;
  expirationDate: string;
  studentId: number;
  meals: Meal[];
}

@Component({
  selector: 'app-my-card',
  standalone: true,
  imports: [
    CommonModule,
    MatCardModule,
    MatButtonModule
  ],
  templateUrl: './my-card.html',
  styleUrls: ['./my-card.scss']
})
export class MyCard implements OnInit {

  card: CardResponse | null = null;
  isLoading = false;

  constructor(
    private nutritionService: NutritionService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.loadCard();
  }

  loadCard(): void {
    this.isLoading = true;

    this.nutritionService.getStudentMyCard().subscribe({
      next: (response: any) => {
        this.card = response.card;
        this.isLoading = false;
        this.cdr.detectChanges();
      },
      error: () => {
        this.card = null;
        this.isLoading = false;
      }
    });
  }

  addFunds(): void {
    if (!this.card) return;

    const amountInput = prompt('Enter amount to add');
    if (!amountInput) return;

    const amount = Number(amountInput);
    if (isNaN(amount) || amount <= 0) {
      alert('Invalid amount');
      return;
    }

    this.nutritionService.addFunds(this.card.id, amount)
      .subscribe({
        next: () => this.loadCard(),
        error: () => alert('Add funds failed')
      });
  }

  getMealAmount(type: 'BREAKFAST' | 'LUNCH' | 'DINNER'): number {
    if (!this.card) return 0;

    const meal = this.card.meals.find(m => m.mealType === type);
    return meal ? meal.amount : 0;
  }
}