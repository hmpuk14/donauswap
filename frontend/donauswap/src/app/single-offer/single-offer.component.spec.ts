import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SingleOfferComponent } from './single-offer.component';

// Testklasse vorinstalliert durch Angular cli ng g c  - nicht verwendet
describe('SingleOfferComponent', () => {
  let component: SingleOfferComponent;
  let fixture: ComponentFixture<SingleOfferComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SingleOfferComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SingleOfferComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
