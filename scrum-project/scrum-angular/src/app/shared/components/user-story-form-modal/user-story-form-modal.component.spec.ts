import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserStoryFormModalComponent } from './user-story-form-modal.component';

describe('UserStoryFormModalComponent', () => {
  let component: UserStoryFormModalComponent;
  let fixture: ComponentFixture<UserStoryFormModalComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserStoryFormModalComponent]
    });
    fixture = TestBed.createComponent(UserStoryFormModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
