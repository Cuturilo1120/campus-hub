import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NutritionLayout } from './nutrition-layout';

describe('NutritionLayout', () => {
  let component: NutritionLayout;
  let fixture: ComponentFixture<NutritionLayout>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NutritionLayout]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NutritionLayout);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
