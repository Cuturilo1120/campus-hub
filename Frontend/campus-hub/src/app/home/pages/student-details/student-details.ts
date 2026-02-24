import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { CommonModule, Location } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { UsersService } from '../../../../services/users-service';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { Observable } from 'rxjs';
import { NutritionService } from '../../../../services/nutrition-service';

@Component({
  selector: 'app-student-details',
  standalone: true,
  imports: [
    CommonModule,
    MatCardModule,
    MatButtonModule,
    MatIconModule
  ],
  templateUrl: './student-details.html',
  styleUrls: ['./student-details.scss']
})
export class StudentDetails implements OnInit {

  student$!: Observable<any>;
  studentId!: number;

  card: any = null;
  isLoading = false;

  constructor(
    private route: ActivatedRoute,
    private usersService: UsersService,
    private router: Router,
    private location: Location,
    private nutritionService: NutritionService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const id = Number(params.get('id'));

      if (id) {
        this.studentId = id;
        this.student$ = this.usersService.getStudentById(id);
        this.loadCard(id);
      }
    });
  }

 loadCard(studentId: number) {
    this.nutritionService.getCardByStudent(studentId).subscribe({
      next: card => {
        this.card = card;
        this.cdr.detectChanges();
      },
      error: () => {
        this.card = null;
        this.cdr.detectChanges();
      }
    }); 
  }

  createCard() {
    if (!confirm('Create card for this student?')) return;

    this.isLoading = true;

    this.nutritionService.createCard(this.studentId).subscribe({
      next: () => {
        this.loadCard(this.studentId);
      },
      error: () => {},
      complete: () => this.isLoading = false
    });
  }

  renewCard() {
    this.nutritionService.renewCard(this.card.id).subscribe({
      next: () => this.loadCard(this.studentId),
      error: () => alert('Renew failed')
    });
  }

  deleteCard() {
    if (!confirm('Delete card?')) return;

    this.nutritionService.deleteCard(this.card.id).subscribe({
      next: () => this.loadCard(this.studentId),
      error: () => alert('Delete failed')
    });
  }

  addFunds() {
    const amount = prompt('Enter amount');

    if (!amount) return;

    this.nutritionService.addFunds(
      this.card.id,
      Number(amount)
    ).subscribe({
      next: () => this.loadCard(this.studentId),
      error: () => alert('Add funds failed')
    });
  }

  deleteStudent() {
    if (!confirm('Delete this student?')) return;

    this.usersService.deleteStudents(this.studentId).subscribe({
      next: () => { 
        if (this.role === 'PRINCIPAL') {
          this.router.navigate(['/dorm/students']);
        }
        else {
          this.router.navigate(['/nutrition/students']);
        }
      },
      error: err => console.error(err)
    });
  }

  goBack() {
    this.location.back();
  }

  get role(): string | null {
    return localStorage.getItem('role');
  }
}