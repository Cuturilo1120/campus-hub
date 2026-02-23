import { CommonModule, Location } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { UsersService } from '../../../../services/users-service';

@Component({
  selector: 'app-register-student',
  imports: [
    ReactiveFormsModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatCardModule,
    CommonModule
  ],
  templateUrl: './register-student.html',
  styleUrl: './register-student.scss',
})
export class RegisterStudent {

  studentForm;
  status = ['BUDGET', 'SELF_FINANCE'];

  constructor(
    private fb: FormBuilder,
    private usersService: UsersService,
    private location: Location,
  ) {
    this.studentForm = this.fb.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      username: ['', Validators.required],
      password: ['', Validators.required],
      indexNumber: ['', Validators.required],
      city: ['', Validators.required],
      facultyName: ['', Validators.required],
      status: ['', Validators.required]
    });
  }

  submit() {
    if (this.studentForm.invalid) return;

    this.usersService.registerStudent(this.studentForm.value as any)
      .subscribe({
        next: () => {
          alert('Student registered successfully');
          this.location.back();
        },
        error: (err) => {
          console.error(err);
          alert('Register failed');
        }
    });
  }

}
