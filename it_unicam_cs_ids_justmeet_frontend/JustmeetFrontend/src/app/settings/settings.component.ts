import { Component, OnInit } from '@angular/core';

import { TokenStorageService } from '../_services/token-storage.service';
import {UserService} from "../_services/user.service";
import {Observable} from "rxjs";
import {settingsPayload} from "./settingsPayload";
import {FormControl, FormGroup} from "@angular/forms";
import {AppComponent} from "../app.component";
import {Router} from "@angular/router";


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
  values = '';


  constructor(private token: TokenStorageService, private userService: UserService, private logoutComponent : AppComponent, private router : Router) { }

  ngOnInit() {
    let allData: string;
    this.currentUser = this.token.getUser();
    this.userService.getUserDetails().subscribe((data : settingsPayload)=>{   //TODO Phy
      allData = JSON.stringify(data);
      this.userDetails = JSON.parse(allData);
    });
    //this.changePasswordForm.controls.proof.patchValue('');
  }

/*
  changePassword(){
    //this.currentUser = this.token.getUser();
    this.changePasswordForm = new FormGroup({
      pass: new FormControl()
    })
   this.userService.setUserPass(this.changePasswordForm.value);
  }
*/

  onSubmit() {
    /*
    this.changePasswordForm = new FormGroup({
      pass: new FormControl()
    });
    */
    alert(this.values);
    this.userService.setUserPass(this.values);


  }

  deleteAccount() {

    if(confirm("Are you sure ?")) {
      this.userService.deleteAcc().subscribe();
      this.token.signOut();
      this.router.navigateByUrl('/home');
    }
  }
}
