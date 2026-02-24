import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { NutritionService } from '../../../../../services/nutrition-service';

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

  card: any = null;
  isLoading = false;

  constructor(
    private nutritionService: NutritionService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.loadCard();
  }

  loadCard() {
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

  addFunds() {
    const amount = prompt('Enter amount');

    if (!amount) return;

    this.nutritionService.addFunds(this.card.id, Number(amount))
      .subscribe({
        next: () => this.loadCard(),
        error: () => alert('Add funds failed')
      });
  }
}