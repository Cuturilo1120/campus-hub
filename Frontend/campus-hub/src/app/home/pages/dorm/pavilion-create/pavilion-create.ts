import { Component, OnInit } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatCardModule } from '@angular/material/card';
import { DormService } from '../../../../../services/dorm-service';

@Component({
  selector: 'app-pavilion-create',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatCardModule
  ],
  templateUrl: './pavilion-create.html',
  styleUrl: './pavilion-create.scss'
})
export class PavilionCreate implements OnInit {

  form;
  dorms: any[] = [];

  constructor(
    private fb: FormBuilder,
    private dormService: DormService,
    private router: Router
  ) {
    this.form = this.fb.group({
      number: ['', Validators.required],
      address: ['', Validators.required],
      dormId: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.dormService.getAllDorms().subscribe({
      next: (data) => this.dorms = data,
      error: (err) => console.error(err)
    });
  }

  submit() {
    if (this.form.invalid) return;

    const { number, address, dormId } = this.form.value;

    this.dormService.createPavilion(Number(number), address!, Number(dormId)).subscribe({
      next: () => this.router.navigate(['/dorm/pavilions']),
      error: (err) => {
        console.error(err);
        alert('Failed to create pavilion');
      }
    });
  }

  goBack() {
    this.router.navigate(['/dorm/pavilions']);
  }
}
