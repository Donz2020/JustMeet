import { Component, OnInit } from '@angular/core';

import { TokenStorageService } from '../_services/token-storage.service';
import {UserService} from "../_services/user.service";
import {Observable} from "rxjs";
import {settingsPayload} from "./settingsPayload";
import {FormControl, FormGroup} from "@angular/forms";



@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css']
})
export class SettingsComponent implements OnInit {
  currentUser: string;
  userDetails : settingsPayload;
  changePasswordForm: FormGroup;
  form: any = {};



  constructor(private token: TokenStorageService, private userService: UserService) { }

  ngOnInit() {
    let allData: string;
    this.currentUser = this.token.getUser();
    this.userService.getUserDetails().subscribe((data : settingsPayload)=>{
      allData = JSON.stringify(data);
      this.userDetails = JSON.parse(allData);
    });
  }


  changePassword(){
    //this.currentUser = this.token.getUser();
    this.changePasswordForm = new FormGroup({
      pass: new FormControl()
    })
   this.userService.setUserPass(this.changePasswordForm.value);
  }


  onSubmit() {
    this.changePasswordForm = new FormGroup({
      pass: new FormControl(this.userService.setUserPass(this.changePasswordForm.value))
    });
    this.userService.setUserPass(this.changePasswordForm.value);

  }

  deleteAccount() {
    this.userService.deleteAcc();

  }
}
