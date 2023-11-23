import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TaskFormModalComponent } from './task-form-modal.component';

describe('TaskFormModalComponent', () => {
  let component: TaskFormModalComponent;
  let fixture: ComponentFixture<TaskFormModalComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TaskFormModalComponent]
    });
    fixture = TestBed.createComponent(TaskFormModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
