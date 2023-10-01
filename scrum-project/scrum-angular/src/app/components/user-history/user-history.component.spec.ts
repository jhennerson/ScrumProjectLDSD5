import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserHistoryComponent } from './user-history.component';

describe('UserHistoryComponent', () => {
  let component: UserHistoryComponent;
  let fixture: ComponentFixture<UserHistoryComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserHistoryComponent]
    });
    fixture = TestBed.createComponent(UserHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
