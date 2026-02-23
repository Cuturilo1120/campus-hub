import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NutritionDashboard } from './nutrition-dashboard';

describe('NutritionDashboard', () => {
  let component: NutritionDashboard;
  let fixture: ComponentFixture<NutritionDashboard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NutritionDashboard]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NutritionDashboard);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
