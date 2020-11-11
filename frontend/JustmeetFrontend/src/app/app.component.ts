import {Component, OnInit} from '@angular/core';
import {TokenStorageService} from './_services/token-storage.service';
import {profilePayload} from "./profile/profilePayload";
import {UserService} from "./_services/user.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  //private roles: string[];
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
      //const user = this.token.getUser();
      //this.roles = user.roles;

      //this.showAdminBoard = this.roles.includes('ADMIN',0);
      //this.showModeratorBoard = this.roles.includes('MOD', 0);


      let allData: string;
      this.currentUser = this.token.getUser();
      this.userService.getUserDetails().subscribe((data: profilePayload) => {
        allData = JSON.stringify(data);
        this.userDetails = JSON.parse(allData);
        this.adminBool();
      });

      // this.username = user.username;
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
