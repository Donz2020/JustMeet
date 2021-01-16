import {Component, OnInit} from '@angular/core';
import {AuthService} from '../_services/auth.service';
import {FormControl, FormGroup} from "@angular/forms";
import {idPayload} from "../utils/registerPayloads/identificatorPayload";
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
  formSignup : FormGroup;
  submitted : boolean = true;
  currentUser: string = null;

  constructor(private token: TokenStorageService, private authService: AuthService, public router: Router) {}

  ngOnInit() {
    this.currentUser = this.token.getUser();
    if (this.currentUser == null){
      this.initForm();}
    else{
      this.router.navigateByUrl('/home');
    }
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
