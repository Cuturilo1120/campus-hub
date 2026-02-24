import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { DormService } from '../../../../../services/dorm-service';

@Component({
  selector: 'app-my-room-applications',
  standalone: true,
  imports: [
    CommonModule,
    MatTableModule,
    MatButtonModule,
    MatIconModule
  ],
  templateUrl: './my-room-applications.html',
  styleUrl: './my-room-applications.scss'
})
export class MyRoomApplications implements OnInit {

  dataSource = new MatTableDataSource<any>();
  displayedColumns: string[] = ['id', 'dormName', 'dateOfApplication', 'status', 'actions'];

  constructor(
    private dormService: DormService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadApplications();
  }

  loadApplications() {
    this.dormService.getMyRoomApplications().subscribe({
      next: (data) => {
        this.dataSource.data = data;
      },
      error: (err) => console.error(err)
    });
  }

  viewApplication(id: number) {
    this.router.navigate(['/student/room-applications', id]);
  }

  apply() {
    this.router.navigate(['/student/room-applications/apply']);
  }
}
