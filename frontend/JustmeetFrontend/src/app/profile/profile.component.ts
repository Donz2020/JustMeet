import {Component, OnInit} from '@angular/core';

import {TokenStorageService} from '../_services/token-storage.service';
import {UserService} from "../_services/user.service";
import {profilePayload} from "../utils/profilePayloads/profilePayload";
import {postService} from "../_services/post.service";
import {postPayload} from "../utils/postPayloads/postPayload";



@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
  currentUser: string;
  userDetails: profilePayload;
  postPayload: Array<postPayload>;
  noPosts: boolean= false;



  constructor(private token: TokenStorageService, private userService: UserService,private postService: postService) {
  }

  ngOnInit() {
    let allData: string;
    let isAdmin = false;
    let isMod = false;
    this.currentUser = this.token.getUser();
    this.userService.getUserDetails().subscribe((data: profilePayload) => {
      allData = JSON.stringify(data);
      this.userDetails = JSON.parse(allData);
      this.userDetails.roles.forEach(value => {
        if (value == "ADMIN") {
          isAdmin = true;

        }else if (value == "MOD"){
          isMod = true;
        }
      });
    });

    this.getUserPosts();
  }



  getUserPosts(){
    if (this.currentUser != ""){
      this.postService.getMyPosts().subscribe(
        (data: Array<postPayload>) => {
          this.postPayload = data;
        },
        error => {
          this.noPosts = true;
        }
      );
    }
  }

}
