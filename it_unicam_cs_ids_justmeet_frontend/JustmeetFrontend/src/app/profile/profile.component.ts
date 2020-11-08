import { Component, OnInit } from '@angular/core';

import { TokenStorageService } from '../_services/token-storage.service';
import {UserService} from "../_services/user.service";
import {Observable} from "rxjs";
import {profilePayload} from "./profilePayload";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
  currentUser: string;
  userDetails : profilePayload;

  constructor(private token: TokenStorageService, private userService: UserService) { }

  ngOnInit() {
    let allData: string;
    this.currentUser = this.token.getUser();
    this.userService.getUserDetails().subscribe((data : profilePayload)=>{
      allData = JSON.stringify(data);
      this.userDetails = JSON.parse(allData);
    });
  }

}
