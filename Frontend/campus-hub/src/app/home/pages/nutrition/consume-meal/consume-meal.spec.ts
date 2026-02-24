import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsumeMeal } from './consume-meal';

describe('ConsumeMeal', () => {
  let component: ConsumeMeal;
  let fixture: ComponentFixture<ConsumeMeal>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ConsumeMeal]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConsumeMeal);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
