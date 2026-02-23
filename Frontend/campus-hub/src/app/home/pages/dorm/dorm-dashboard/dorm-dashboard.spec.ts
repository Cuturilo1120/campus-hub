import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DormDashboard } from './dorm-dashboard';

describe('DormDashboard', () => {
  let component: DormDashboard;
  let fixture: ComponentFixture<DormDashboard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DormDashboard]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DormDashboard);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
