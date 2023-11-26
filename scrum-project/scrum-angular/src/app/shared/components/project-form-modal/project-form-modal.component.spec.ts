import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProjectFormModalComponent } from './project-form-modal.component';

describe('ProjectFormModalComponent', () => {
  let component: ProjectFormModalComponent;
  let fixture: ComponentFixture<ProjectFormModalComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ProjectFormModalComponent]
    });
    fixture = TestBed.createComponent(ProjectFormModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
