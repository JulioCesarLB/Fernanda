import { TestBed } from '@angular/core/testing';

import { ConsultaServices } from './consulta-services';

describe('ConsultaServices', () => {
  let service: ConsultaServices;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ConsultaServices);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
