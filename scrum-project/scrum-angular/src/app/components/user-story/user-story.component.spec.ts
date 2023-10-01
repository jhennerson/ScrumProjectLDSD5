import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserStoryComponent } from './user-story.component';

describe('UserStoryComponent', () => {
  let component: UserStoryComponent;
  let fixture: ComponentFixture<UserStoryComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserStoryComponent],
    });
    fixture = TestBed.createComponent(UserStoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
