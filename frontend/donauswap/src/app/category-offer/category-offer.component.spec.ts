import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CategoryOfferComponent } from './category-offer.component';

// Testklasse vorinstalliert durch Angular cli ng g c  - nicht verwendet
describe('CategoryOfferComponent', () => {
  let component: CategoryOfferComponent;
  let fixture: ComponentFixture<CategoryOfferComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CategoryOfferComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CategoryOfferComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
