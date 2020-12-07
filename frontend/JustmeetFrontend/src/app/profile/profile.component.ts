import {Component, OnInit} from '@angular/core';

import {TokenStorageService} from '../_services/token-storage.service';
import {UserService} from "../_services/user.service";
import {utilsPayload} from "../utils/utils.component";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
  currentUser: string;
  userDetails: utilsPayload;

  constructor(private token: TokenStorageService, private userService: UserService) {
  }

  ngOnInit() {
    let allData: string;
    let isAdmin = false;
    let isMod = false;
    this.currentUser = this.token.getUser();
    this.userService.getUserDetails().subscribe((data: utilsPayload) => {
      allData = JSON.stringify(data);
      this.userDetails = JSON.parse(allData);


      this.userDetails.roles.forEach(value => {
        if (value == "ADMIN") {
          isAdmin = true;

        }else if (value == "MOD"){
          isMod = true;
        }
      });

      alert(isAdmin);
      alert(isMod);
    });
  }

}
