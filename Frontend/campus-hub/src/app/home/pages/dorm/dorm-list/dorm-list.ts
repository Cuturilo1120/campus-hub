import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { DormService } from '../../../../../services/dorm-service';

@Component({
  selector: 'app-dorm-list',
  standalone: true,
  imports: [
    CommonModule,
    MatTableModule,
    MatButtonModule,
    MatIconModule
  ],
  templateUrl: './dorm-list.html',
  styleUrl: './dorm-list.scss'
})
export class DormList implements OnInit {

  dataSource = new MatTableDataSource<any>();
  displayedColumns: string[] = ['id', 'name', 'city', 'actions'];

  constructor(
    private dormService: DormService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadDorms();
  }

  loadDorms() {
    this.dormService.getAllDorms().subscribe({
      next: (data) => {
        this.dataSource.data = data;
      },
      error: (err) => console.error(err)
    });
  }

  viewDorm(id: number) {
    this.router.navigate(['/dorm/dorms', id]);
  }
}
