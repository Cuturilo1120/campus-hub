import { Component } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatCardModule } from '@angular/material/card';
import { DormService } from '../../../../../services/dorm-service';

@Component({
  selector: 'app-dorm-create',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatCardModule
  ],
  templateUrl: './dorm-create.html',
  styleUrl: './dorm-create.scss'
})
export class DormCreate {

  form;

  constructor(
    private fb: FormBuilder,
    private dormService: DormService,
    private router: Router
  ) {
    this.form = this.fb.group({
      name: ['', Validators.required],
      city: ['', Validators.required]
    });
  }

  submit() {
    if (this.form.invalid) return;

    const { name, city } = this.form.value;

    this.dormService.createDorm(name!, city!).subscribe({
      next: () => this.router.navigate(['/dorm/dorms']),
      error: (err) => {
        console.error(err);
        alert('Failed to create dorm');
      }
    });
  }

  goBack() {
    this.router.navigate(['/dorm/dorms']);
  }
}
