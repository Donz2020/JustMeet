import {Component, OnInit} from '@angular/core';
import {AuthService} from '../_services/auth.service';
import {FormControl, FormGroup} from "@angular/forms";
import {idPayload} from "../utils/registerPayloads/identificatorPayload";
import {identificatorPayloadUser} from "../utils/registerPayloads/identificatorPayloadUser";
import {TokenStorageService} from "../_services/token-storage.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  isSignUpFailed = false;
  isSuccessful = false;
  errorMessage = '';
  formSignup: FormGroup;
  formSignupVRF: FormGroup;
  submitted: boolean = true;
  currentUser: string = null;

  constructor(private token: TokenStorageService, private authService: AuthService, public router: Router) {
  }

  ngOnInit() {
    this.currentUser = this.token.getUser();
    if (this.currentUser == null) {
      this.initForm();
    } else {
      this.router.navigateByUrl('/home');
    }
  }

  initForm(): void {
    this.submitted = false;
    this.initSTDForm();
    this.initVRFForm();
  }

  //Standard User Form
  initSTDForm() {
    this.formSignup = new FormGroup({
      email: new FormControl(''),
      pass: new FormControl(''),
      name: new FormControl(''),
      surname: new FormControl(''),
      birthDate: new FormControl(''),
    });
  }

  // BusinessUser Form
  initVRFForm() {
    this.formSignupVRF = new FormGroup({
      VATNumber: new FormControl(''),
      password: new FormControl(''),
      VATname: new FormControl(''),
    });
  }

  //Submit Standard User form
  onSubmit() {
    this.setSubmitted();
    if (this.formSignup.valid) {
      let idPayload: identificatorPayloadUser = {
        username: this.formSignup.get('email').value,
        password: this.formSignup.get('pass').value,
        name: this.formSignup.get('name').value,
        surname: this.formSignup.get('surname').value,
        birthDate: this.formSignup.get('birthDate').value,

      };
      this.authService.register(idPayload).subscribe(
        (data: idPayload) => {
          data = idPayload;
          this.setSignupBool();
          this.redirectLogin();
        },
        err => {
          this.errorMessage = err.error.message;
          this.setSignupFail();
        }
      );
    }
    ;
  }

  //Sumbit BusinessUser Form
  onSubmitVRF() {
    this.setSubmitted();
    if (this.formSignupVRF.valid) {
      let idPayloadVRF: idPayload = {
        username: this.formSignupVRF.get('VATNumber').value,
        password: this.formSignupVRF.get('password').value,
        name: this.formSignupVRF.get('VATname').value,
      };
      this.authService.registerBusiness(idPayloadVRF).subscribe(
        (data: idPayload) => {
          data = idPayloadVRF;
          this.setSignupBool();
          this.redirectLogin();
        },
        err => {
          this.errorMessage = err.error.message;
          this.setSignupFail();
        }
      );
    }
  }

  setSubmitted() {
    this.submitted = true;
  }

  setSignupBool() {
    this.isSignUpFailed = false;
    this.isSuccessful = true;
  }

  setSignupFail() {
    this.isSignUpFailed = true;
  }

  redirectLogin() {
    window.location.href = "/login";
  }


//Gestione Eventi

  keyDownFunction(event) {
    if (this.submitted) {
      this.submitted = false;
    }
    if (event.keyCode === 13) {
      this.setSubmitted();
      this.formSignup.validator;
      if (this.formSignup.validator) {
        this.onSubmit();
      }
    }
  }

  onTabChanged($event) {
    this.submitted = false;
    this.isSignUpFailed = false;
    this.isSuccessful = false;
  }

}
