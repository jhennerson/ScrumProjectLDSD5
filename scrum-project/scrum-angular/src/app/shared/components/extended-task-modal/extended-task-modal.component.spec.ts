import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExtendedTaskModalComponent } from './extended-task-modal.component';

describe('ExtendedTaskModalComponent', () => {
  let component: ExtendedTaskModalComponent;
  let fixture: ComponentFixture<ExtendedTaskModalComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ExtendedTaskModalComponent]
    });
    fixture = TestBed.createComponent(ExtendedTaskModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
