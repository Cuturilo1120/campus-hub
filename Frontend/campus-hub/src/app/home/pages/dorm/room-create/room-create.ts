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
  selector: 'app-room-create',
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
  templateUrl: './room-create.html',
  styleUrl: './room-create.scss'
})
export class RoomCreate implements OnInit {

  form;
  pavilions: any[] = [];

  constructor(
    private fb: FormBuilder,
    private dormService: DormService,
    private router: Router
  ) {
    this.form = this.fb.group({
      roomNumber: ['', Validators.required],
      capacity: ['', Validators.required],
      pavilionId: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.dormService.getAllPavilions().subscribe({
      next: (data) => this.pavilions = data,
      error: (err) => console.error(err)
    });
  }

  submit() {
    if (this.form.invalid) return;

    const { roomNumber, capacity, pavilionId } = this.form.value;

    this.dormService.createRoom(roomNumber!, Number(capacity), Number(pavilionId)).subscribe({
      next: () => this.router.navigate(['/dorm/rooms']),
      error: (err) => {
        console.error(err);
        alert('Failed to create room');
      }
    });
  }

  goBack() {
    this.router.navigate(['/dorm/rooms']);
  }
}
