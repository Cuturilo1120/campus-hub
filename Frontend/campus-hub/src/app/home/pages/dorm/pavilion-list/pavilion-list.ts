import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { DormService } from '../../../../../services/dorm-service';

@Component({
  selector: 'app-pavilion-list',
  standalone: true,
  imports: [
    CommonModule,
    MatTableModule,
    MatButtonModule,
    MatIconModule
  ],
  templateUrl: './pavilion-list.html',
  styleUrl: './pavilion-list.scss'
})
export class PavilionList implements OnInit {

  dataSource = new MatTableDataSource<any>();
  displayedColumns: string[] = ['id', 'number', 'address', 'actions'];

  constructor(
    private dormService: DormService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadPavilions();
  }

  loadPavilions() {
    this.dormService.getAllPavilions().subscribe({
      next: (data) => {
        this.dataSource.data = data;
      },
      error: (err) => console.error(err)
    });
  }

  viewPavilion(id: number) {
    this.router.navigate(['/dorm/pavilions', id]);
  }
}
