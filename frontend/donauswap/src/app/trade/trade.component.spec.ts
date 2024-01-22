import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HandelnComponent } from './handeln.component';

// Testklasse vorinstalliert durch Angular cli ng g c  - nicht verwendet
describe('HandelnComponent', () => {
  let component: HandelnComponent;
  let fixture: ComponentFixture<HandelnComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HandelnComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HandelnComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
