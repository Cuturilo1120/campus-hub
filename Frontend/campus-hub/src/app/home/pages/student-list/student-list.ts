import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { UsersService } from '../../../../services/users-service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-student-list',
  imports: [
    CommonModule,
    MatTableModule,
    MatButtonModule,
    MatIconModule
  ],
  templateUrl: './student-list.html',
  styleUrl: './student-list.scss',
})
export class StudentList implements OnInit {

  dataSource = new MatTableDataSource<any>();
  displayedColumns: string[] = [
    'firstName',
    'lastName',
    'username',
    'indexNumber',
    'city',
    'facultyName',
    'status',
    'actions'
  ];

  constructor(
    private usersService: UsersService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadStudents();
  }

  loadStudents() {
   this.usersService.getAllStudents().subscribe({
      next: (data) => {
        this.dataSource.data = data;
      },
      error: (err) => console.error(err)
    });
  }

  viewStudent(id: number) {
    if (this.role === 'PRINCIPAL') {
      this.router.navigate(['/dorm/students', id]);
    }
    else {
      this.router.navigate(['/nutrition/students', id]);
    }
  }

  get role(): string | null {
    return localStorage.getItem('role');
  }

}
