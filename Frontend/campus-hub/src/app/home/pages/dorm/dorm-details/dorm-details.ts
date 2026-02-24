import { Component, OnInit } from '@angular/core';
import { CommonModule, Location } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { DormService } from '../../../../../services/dorm-service';

@Component({
  selector: 'app-dorm-details',
  standalone: true,
  imports: [
    CommonModule,
    MatCardModule,
    MatButtonModule,
    MatIconModule
  ],
  templateUrl: './dorm-details.html',
  styleUrl: './dorm-details.scss'
})
export class DormDetails implements OnInit {

  dorm$!: Observable<any>;
  capacity: number | null = null;

  constructor(
    private route: ActivatedRoute,
    private dormService: DormService,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const id = Number(params.get('id'));
      if (id) {
        this.dorm$ = this.dormService.getDormById(id);
        this.dormService.getDormCapacity(id).subscribe({
          next: (cap) => this.capacity = cap,
          error: () => this.capacity = null
        });
      }
    });
  }

  goBack() {
    this.location.back();
  }
}
