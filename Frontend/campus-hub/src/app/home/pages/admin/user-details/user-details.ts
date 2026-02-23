import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { UsersService } from '../../../../../services/users-service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-user-details',
  standalone: true,
  imports: [
    CommonModule,
    MatCardModule,
    MatButtonModule,
    MatIconModule
  ],
  templateUrl: './user-details.html',
  styleUrls: ['./user-details.scss']
})
export class UserDetails implements OnInit {

  employee$!: Observable<any>;
  employeeId!: number;

  constructor(
    private route: ActivatedRoute,
    private usersService: UsersService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const id = Number(params.get('id'));
      this.employeeId = id;
      this.employee$ = this.usersService.getEmployeeById(id);
    });
  }

  deleteUser() {
    if (!confirm('Delete this employee?')) return;

    this.usersService.deleteEmployee(this.employeeId).subscribe({
      next: () => this.router.navigate(['/admin/users']),
      error: (err) => console.error(err)
    });
  }

  goBack() {
    this.router.navigate(['/admin/users']);
  }
}