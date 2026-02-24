import { Component, OnInit } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatCardModule } from '@angular/material/card';
import { DormService } from '../../../../../services/dorm-service';

@Component({
  selector: 'app-room-application-apply',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatButtonModule,
    MatFormFieldModule,
    MatSelectModule,
    MatCardModule
  ],
  templateUrl: './room-application-apply.html',
  styleUrl: './room-application-apply.scss'
})
export class RoomApplicationApply implements OnInit {

  dorms: any[] = [];

  form;

  constructor(
    private fb: FormBuilder,
    private dormService: DormService,
    private router: Router
  ) {
    this.form = this.fb.group({
      dormId: [null, Validators.required]
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

    const dormId = this.form.value.dormId!;

    this.dormService.applyForRoom(dormId).subscribe({
      next: () => this.router.navigate(['/student/room-applications']),
      error: (err) => {
        console.error(err);
        alert(err.error?.message || 'Failed to submit application');
      }
    });
  }

  goBack() {
    this.router.navigate(['/student/room-applications']);
  }
}
