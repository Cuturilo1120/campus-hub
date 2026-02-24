import { Component, OnInit } from '@angular/core';
import { CommonModule, Location } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { DormService } from '../../../../../services/dorm-service';

@Component({
  selector: 'app-pavilion-details',
  standalone: true,
  imports: [
    CommonModule,
    MatCardModule,
    MatButtonModule,
    MatIconModule
  ],
  templateUrl: './pavilion-details.html',
  styleUrl: './pavilion-details.scss'
})
export class PavilionDetails implements OnInit {

  pavilion$!: Observable<any>;

  constructor(
    private route: ActivatedRoute,
    private dormService: DormService,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const id = Number(params.get('id'));
      if (id) {
        this.pavilion$ = this.dormService.getPavilionById(id);
      }
    });
  }

  goBack() {
    this.location.back();
  }
}
