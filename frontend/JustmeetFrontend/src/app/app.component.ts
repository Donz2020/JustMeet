import {Component, OnInit} from '@angular/core';
import {TokenStorageService} from './_services/token-storage.service';
import {UserService} from "./_services/user.service";
import {profilePayload} from "./utils/profilePayloads/profilePayload";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  isLoggedIn = false;
  isAdmin = false;
  showModeratorBoard = false;
  username: string;
  userDetails: profilePayload;
  currentUser: string;

  constructor(private token: TokenStorageService, private userService: UserService) {
  }

  ngOnInit() {
    this.isLoggedIn = !!this.token.getToken();

    if (this.isLoggedIn) {
      let allData: string;
      this.currentUser = this.token.getUser();
      this.userService.getUserDetails().subscribe((data: profilePayload) => {
        allData = JSON.stringify(data);
        this.userDetails = JSON.parse(allData);
        this.adminBool();
      });
    }
  }

  logout() {
    this.token.signOut();
    window.location.reload();
  }

  adminBool() {
    this.isAdmin = this.userDetails.roles.includes('ADMIN');
  }
}
