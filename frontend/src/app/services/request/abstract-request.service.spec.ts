import { TestBed } from '@angular/core/testing';

import { AbstractRequestService } from './abstract-request.service';

describe('AbstractRequestService', () => {
  let service: AbstractRequestService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AbstractRequestService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
