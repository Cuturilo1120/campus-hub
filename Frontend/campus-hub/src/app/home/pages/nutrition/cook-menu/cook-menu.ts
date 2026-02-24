import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { NutritionService } from '../../../../../services/nutrition-service';

@Component({
  selector: 'app-cook-menu',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatCardModule,
    MatButtonModule,
    MatInputModule
  ],
  templateUrl: './cook-menu.html',
  styleUrls: ['./cook-menu.scss']
})
export class CookMenu implements OnInit {

  form!: FormGroup;
  isLoading = false;
  message: string | null = null;

  constructor(
    private fb: FormBuilder,
    private nutritionService: NutritionService
  ) {}

  ngOnInit(): void {
    this.initForm();
    this.loadMenu();
  }

  initForm() {
    this.form = this.fb.group({
      breakfastPriceBudget: [],
      lunchPriceBudget: [],
      dinnerPriceBudget: [],
      breakfastPriceSelfFinance: [],
      lunchPriceSelfFinance: [],
      dinnerPriceSelfFinance: []
    });
  }

  loadMenu() {
    this.isLoading = true;

    this.nutritionService.getCookMenu().subscribe({
      next: (menu) => {
        this.form.patchValue(menu);
        this.isLoading = false;
      },
      error: () => {
        this.message = 'Failed to load menu';
        this.isLoading = false;
      }
    });
  }

  save() {
    if (this.form.invalid) return;

    this.message = null;
    this.isLoading = true;

    this.nutritionService.updateMenu(this.form.value).subscribe({
      next: () => {
        this.message = 'Menu successfully updated';
        this.isLoading = false;
      },
      error: (err) => {
        if (err.status === 403) {
          this.message = 'Only cook or admin can update menu';
        } else {
          this.message = 'Update failed';
        }
        this.isLoading = false;
      }
    });
  }
}