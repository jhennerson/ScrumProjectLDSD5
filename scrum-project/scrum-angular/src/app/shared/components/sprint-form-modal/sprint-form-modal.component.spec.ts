import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SprintFormModalComponent } from './sprint-form-modal.component';

describe('SprintFormModalComponent', () => {
  let component: SprintFormModalComponent;
  let fixture: ComponentFixture<SprintFormModalComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SprintFormModalComponent]
    });
    fixture = TestBed.createComponent(SprintFormModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
