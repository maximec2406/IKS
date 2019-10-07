import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AccountControllerComponent } from './account-controller.component';

describe('AccountControllerComponent', () => {
  let component: AccountControllerComponent;
  let fixture: ComponentFixture<AccountControllerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AccountControllerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AccountControllerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
