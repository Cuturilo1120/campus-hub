import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CookMenu } from './cook-menu';

describe('CookMenu', () => {
  let component: CookMenu;
  let fixture: ComponentFixture<CookMenu>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CookMenu]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CookMenu);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
