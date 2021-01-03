import {Component, OnInit} from '@angular/core';

import {TokenStorageService} from '../_services/token-storage.service';
import {UserService} from "../_services/user.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AppComponent} from "../app.component";
import {Router} from "@angular/router";
import {ModalService} from "../_modal";
import {profilePayload} from "../utils/profilePayloads/profilePayload";

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.scss']
})


export class SettingsComponent implements OnInit {
  currentUser: string;
  userDetails: profilePayload;
  formSettings: FormGroup;
  submitted: boolean = true;

  constructor(private token: TokenStorageService,
              private userService: UserService,
              private logoutComponent: AppComponent,
              private router: Router,
              private modalService: ModalService) {

  }

  ngOnInit() {
    let allData: string;
    this.currentUser = this.token.getUser();
    this.userService.getUserDetails().subscribe((data: profilePayload) => {   //TODO Phy
      allData = JSON.stringify(data);
      this.userDetails = JSON.parse(allData);
    });
    this.initForm();
  }

  deleteAccount() {
    if (confirm("Are you sure ?")) {
      this.userService.deleteAcc().subscribe();
      this.token.signOut();
      this.router.navigateByUrl('/home');
    }
  }

  // Modal Form
  initForm(): void {
    this.submitted = false;
    this.formSettings = new FormGroup({
      newPass: new FormControl(''),
      confirmPass: new FormControl(''),
    });
  }

  onSubmit() {
    this.submitted = true;
    if (this.formSettings.valid && this.formSettings.get('passName').value == this.formSettings.get('confirmPass').value) {
      this.userService.setUserPass(this.formSettings.get('newPass').value).subscribe();
      this.closePassModal();
    }
  }

  closePassModal() {
    this.modalService.close("passwordModal");
  }

  openPassModal() {
    this.initForm();
    this.modalService.open('passwordModal');
  }

  keyDownFunction(event) {
    if (this.submitted) {
      this.submitted = false;
    }
    if (event.keyCode === 13) {
      this.setSubmitted();
      this.formSettings.validator;
      if (this.formSettings.validator) {
        this.onSubmit();
      }
    }
  }

  setSubmitted() {
    this.submitted = true;
  }
}
