import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WasteBinComponent } from './waste-bin.component';

describe('WasteBinComponent', () => {
  let component: WasteBinComponent;
  let fixture: ComponentFixture<WasteBinComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [WasteBinComponent]
    });
    fixture = TestBed.createComponent(WasteBinComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
