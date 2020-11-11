import { Component, OnInit } from '@angular/core';
import { UserService } from '../_services/user.service';
import {TokenStorageService} from "../_services/token-storage.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  //content: string;
  currentUser: string;

  constructor(private token: TokenStorageService,private userService: UserService) { }

  ngOnInit() {
    /*this.userService.getPublicContent().subscribe(
      data => {
        this.content = data;
      },
      err => {
        this.content = JSON.parse(err.error).message;
      }
    );
    */

    this.currentUser = this.token.getUser();



  }
}
