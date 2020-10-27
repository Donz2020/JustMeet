import { Component, OnInit } from '@angular/core';

import { TokenStorageService } from '../_services/token-storage.service';
import {UserService} from "../_services/user.service";
import {Observable} from "rxjs";
import {settingsPayload} from "./settingsPayload";

@Component({
  selector: 'settings-profile',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css']
})
export class SettingsComponent implements OnInit {
  currentUser: string;
  userDetails : settingsPayload;

  constructor(private token: TokenStorageService, private userService: UserService) { }

  ngOnInit() {
    let allData: string;
    this.currentUser = this.token.getUser();
    this.userService.getUserDetails().subscribe((data : settingsPayload)=>{
      allData = JSON.stringify(data);
      this.userDetails = JSON.parse(allData);
    });
  }

}