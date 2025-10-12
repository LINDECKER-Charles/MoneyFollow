import { TestBed } from '@angular/core/testing';
import { CanActivateFn } from '@angular/router';

import { noVerifiedGuard } from './no-verified.guard';

describe('noVerifiedGuard', () => {
  const executeGuard: CanActivateFn = (...guardParameters) => 
      TestBed.runInInjectionContext(() => noVerifiedGuard(...guardParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
