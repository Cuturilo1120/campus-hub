import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DormLayout } from './dorm-layout';

describe('DormLayout', () => {
  let component: DormLayout;
  let fixture: ComponentFixture<DormLayout>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DormLayout]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DormLayout);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
