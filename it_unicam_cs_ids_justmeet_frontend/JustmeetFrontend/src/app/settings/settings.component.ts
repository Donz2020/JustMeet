import { Component, OnInit } from '@angular/core';

import { TokenStorageService } from '../_services/token-storage.service';
import {UserService} from "../_services/user.service";
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
  formSettings : FormGroup;

  constructor(private token: TokenStorageService, private userService: UserService, private logoutComponent : AppComponent, private router : Router) { }

  ngOnInit() {
    let allData: string;
    this.currentUser = this.token.getUser();
    this.userService.getUserDetails().subscribe((data : settingsPayload)=>{   //TODO Phy
      allData = JSON.stringify(data);
      this.userDetails = JSON.parse(allData);
    });
    this.initForm();
  }

  initForm(): void{
    this.formSettings = new FormGroup({
      newPass: new FormControl(''),
    });
  };

  onSubmit() {
    this.userService.setUserPass(this.formSettings.get('newPass').value).subscribe();
  }



  deleteAccount() {

    if(confirm("Are you sure ?")) {
      this.userService.deleteAcc().subscribe();
      this.token.signOut();
      this.router.navigateByUrl('/home');
    }
  }
}
