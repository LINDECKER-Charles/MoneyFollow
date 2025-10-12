import { TestBed } from '@angular/core/testing';

import { SecurityRequestService } from './security-request.service';

describe('SecurityRequestService', () => {
  let service: SecurityRequestService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SecurityRequestService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
