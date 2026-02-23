import { Component } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatCardModule } from '@angular/material/card';
import { CommonModule } from '@angular/common';
import { UsersService } from '../../../../../services/users-service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatCardModule,
    CommonModule
  ],
  templateUrl: './register.html',
  styleUrls: ['./register.scss']
})
export class Register {

  form;
  roles = ['ADMIN', 'CASHIER', 'PRINCIPAL', 'COOK'];

  constructor(
    private fb: FormBuilder,
    private usersService: UsersService,
    private router: Router,
  ) {
    this.form = this.fb.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      username: ['', Validators.required],
      password: ['', Validators.required],
      role: ['ADMIN', Validators.required]
    });
  }

  submit() {
    if (this.form.invalid) return;

    this.usersService.registerUser(this.form.value as any)
      .subscribe({
        next: () => {
          alert('User registered successfully');
          this.router.navigate(['/admin']);
        },
        error: (err) => {
          console.error(err);
          alert('Register failed');
        }
      });
  }
}