import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SprintsComponent } from './sprints.component';

describe('SprintsComponent', () => {
  let component: SprintsComponent;
  let fixture: ComponentFixture<SprintsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SprintsComponent]
    });
    fixture = TestBed.createComponent(SprintsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
