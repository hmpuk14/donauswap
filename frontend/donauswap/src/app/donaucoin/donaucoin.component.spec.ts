import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DonaucoinComponent } from './donaucoin.component';

// Testklasse vorinstalliert durch Angular cli ng g c  - nicht verwendet
describe('DonaucoinComponent', () => {
  let component: DonaucoinComponent;
  let fixture: ComponentFixture<DonaucoinComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DonaucoinComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DonaucoinComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
