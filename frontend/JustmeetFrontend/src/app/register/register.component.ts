/*import {Component, OnInit} from '@angular/core';
import {AuthService} from '../_services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  form: any = {};
  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';


  constructor(private authService: AuthService) {}

  ngOnInit() {}

  onSubmit() {
    this.authService.register(this.form).subscribe(
      data => {
        console.log(data);
        this.isSuccessful = true;
        this.isSignUpFailed = false;
        window.location.href = "/login";
      },
      err => {
        this.errorMessage = err.error.message;
        this.isSignUpFailed = true;
      }
    );
  }
}*/

import {Component, OnInit} from '@angular/core';
import {AuthService} from '../_services/auth.service';
import {FormControl, FormGroup} from "@angular/forms";
import {idPayload} from "../utils/registerPayloads/identificatorPayload";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  isSignUpFailed = false;
  isSuccessful = false;
  errorMessage = '';
  formSignup : FormGroup;
  submitted : boolean = true;

  constructor(private authService: AuthService) {}

  ngOnInit() {
    this.initForm();
  }

  initForm(): void {
    this.submitted = false;
    this.formSignup = new FormGroup({
      email: new FormControl(''),
      pass: new FormControl(''),
      name: new FormControl(''),
      surname: new FormControl(''),
      birthDate: new FormControl(''),
    });
  }

  onSubmit() {
    this.submitted = true;
    if (this.formSignup.valid) {
      let idPayload : idPayload = {
        username: this.formSignup.get('email').value,
        password: this.formSignup.get('pass').value,
      };
      this.authService.register(idPayload).subscribe(
        (data : idPayload ) => {
          data = idPayload;
          console.log(data);
          this.isSignUpFailed = false;
          this.isSuccessful = true;
          window.location.href = "/login";
        },
        err => {
          this.errorMessage = err.error.message;
          this.isSignUpFailed = true;
        }
      );
    };
  }
  keyDownFunction(event){
    if (this.submitted){
      this.submitted = false;
    }
    if (event.keyCode === 13) {
      this.setSubmitted();
      this.formSignup.validator;
      if (this.formSignup.validator){
        this.onSubmit();
      }
    }
  }

  setSubmitted(){
    this.submitted = true;
  }
}
