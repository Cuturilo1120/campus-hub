import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { DormService } from '../../../../../services/dorm-service';

@Component({
  selector: 'app-dorm-stay-list',
  standalone: true,
  imports: [
    CommonModule,
    MatTableModule,
    MatButtonModule,
    MatIconModule
  ],
  templateUrl: './dorm-stay-list.html',
  styleUrl: './dorm-stay-list.scss'
})
export class DormStayList implements OnInit {

  dataSource = new MatTableDataSource<any>();
  displayedColumns: string[] = ['id', 'studentId', 'moveInDate', 'moveOutDate', 'stayStatus', 'actions'];

  constructor(
    private dormService: DormService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadDormStays();
  }

  loadDormStays() {
    this.dormService.getAllDormStays().subscribe({
      next: (data) => {
        this.dataSource.data = data;
      },
      error: (err) => console.error(err)
    });
  }

  viewDormStay(id: number) {
    this.router.navigate(['/dorm/dorm-stays', id]);
  }
}
