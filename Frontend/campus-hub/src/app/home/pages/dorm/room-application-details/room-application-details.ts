import { Component, OnInit } from '@angular/core';
import { CommonModule, Location } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { DormService } from '../../../../../services/dorm-service';

@Component({
  selector: 'app-room-application-details',
  standalone: true,
  imports: [
    CommonModule,
    MatCardModule,
    MatButtonModule,
    MatIconModule
  ],
  templateUrl: './room-application-details.html',
  styleUrl: './room-application-details.scss'
})
export class RoomApplicationDetails implements OnInit {

  application: any = null;
  loadError: string | null = null;
  isPrincipal = false;
  actionInProgress = false;

  constructor(
    private route: ActivatedRoute,
    private dormService: DormService,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.isPrincipal = this.getRole() === 'ROLE_PRINCIPAL';

    this.route.paramMap.subscribe(params => {
      const id = Number(params.get('id'));
      if (id) {
        this.application = null;
        this.loadError = null;
        this.dormService.getRoomApplicationById(id).subscribe({
          next: (data) => this.application = data,
          error: (err) => {
            console.error(err);
            this.loadError = err.error?.message || 'Failed to load application.';
          }
        });
      }
    });
  }

  get isResolved(): boolean {
    return this.application?.status === 'ACCEPTED' || this.application?.status === 'REJECTED';
  }

  accept() {
    if (this.actionInProgress || this.isResolved) return;
    this.actionInProgress = true;
    this.dormService.acceptRoomApplication(this.application.id).subscribe({
      next: () => {
        this.application.status = 'ACCEPTED';
        this.actionInProgress = false;
      },
      error: (err) => {
        console.error(err);
        alert(err.error?.message || 'Failed to accept application');
        this.actionInProgress = false;
      }
    });
  }

  reject() {
    if (this.actionInProgress || this.isResolved) return;
    this.actionInProgress = true;
    this.dormService.rejectRoomApplication(this.application.id).subscribe({
      next: () => {
        this.application.status = 'REJECTED';
        this.actionInProgress = false;
      },
      error: (err) => {
        console.error(err);
        alert(err.error?.message || 'Failed to reject application');
        this.actionInProgress = false;
      }
    });
  }

  goBack() {
    this.location.back();
  }

  private getRole(): string | null {
    const token = localStorage.getItem('token');
    if (!token) return null;
    try {
      const payload = JSON.parse(atob(token.split('.')[1].replace(/-/g, '+').replace(/_/g, '/')));
      return payload.role ?? null;
    } catch {
      return null;
    }
  }
}
