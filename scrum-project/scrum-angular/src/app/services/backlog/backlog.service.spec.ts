import { TestBed } from '@angular/core/testing';

import { BacklogService } from './backlog.service';

describe('BacklogService', () => {
  let service: BacklogService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BacklogService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
