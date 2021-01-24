import {Component, OnInit} from '@angular/core';
import {AppComponent} from "../app.component";
import {ActivatedRoute, Router} from "@angular/router";
import {TokenStorageService} from "../_services/token-storage.service";
import {UserService} from "../_services/user.service";
import {postService} from "../_services/post.service";
import {postPayload} from "../utils/postPayloads/postPayload";
import {Location} from '@angular/common';
import {profilePayload} from "../utils/profilePayloads/profilePayload";
import {changeUserRolePayload} from "../utils/profilePayloads/changeUserRolePayload";
import {userDetailsPayload} from "../utils/postPayloads/userDetailsPayload";


@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.scss']
})


export class PostComponent implements OnInit {
  postPayload: postPayload;
  userDetailsPayload: userDetailsPayload;
  currentUser: string;
  errorMessage: string;
  isOwner = false;
  userDetails: changeUserRolePayload;
  id: number = null;
  user: string


  //latitude = 43.439445;
  //longitude = 13.65975;
  //googleMapType = 'hybrid';

  constructor(private token: TokenStorageService,
              private logoutComponent: AppComponent,
              private route: ActivatedRoute,
              private postService: postService,
              private location: Location,
              private userService: UserService,
              public router: Router
  ) {

  }

  ngOnInit() {
    this.getUser();
    this.route.params.subscribe(
      params => {
        this.id = params['id'];
        if (this.id != null) {
          this.getPostDetail(this.id);
        }
      });

  }


  getUser() {
    this.currentUser = "";
    this.currentUser = this.token.getUser();
  }

  getPostDetail(id: number) {
    this.postService.getPost(id)
      .subscribe(
        response => this.postPayload = response,
        error => this.errorMessage = <any>error);
  }

  subscribeToPost(id: number) {
    this.postService.subscribePost(id)
      .subscribe(
        response => this.postPayload.id = response,
        error => this.errorMessage = <any>error);
    this.reloadPage();

  }


  deleteSubscribePost(id: number) {
    this.postService.deleteSubPost(id)
      .subscribe(
        response => this.postPayload.id = response,
        error => this.errorMessage = <any>error);
    this.reloadPage();
  }


  checkOwnerPost(id: number) {
    this.postService.getOwnerPost(id)
      .subscribe(
        response => this.postPayload.id = response,
        error => this.errorMessage = <any>error);
  }


  getCurrentUser() {
    this.userService.getUserDetails()
      .subscribe(
        response => this.userDetailsPayload.username = response,
        error => this.errorMessage = <any>error);
  }

  checkOwner(id){
    return this.checkOwnerPost(id) == this.getCurrentUser();

  }

  deleteMyPost(id) {
    if (this.checkOwner(id)) {
      this.postService.deletePost(id)
        .subscribe(
          response => this.postPayload.id = response,
          error => this.errorMessage = <any>error);
          this.isOwner = true;
          window.location.href = "/home";

    } else {
      this.isOwner = false;
    }
  }


  reloadPage() {
    window.location.reload();
  }

  back() {
    this.location.back();
  }

}

/*
  checkOwner() {
    let allData : string;
    let Owner : string;

    //this.currentUser = this.token.getUser();

    //todo filtrare response con ownername

    let OwnerPost = this.postService.getPost(this.id).subscribe((data : postPayload) => {
      Owner = JSON.stringify(data);
      //this.postPayload.ownerName = JSON.parse(Owner);
    });


    let User = this.userService.getUserDetails().subscribe((data: profilePayload) => {
      allData = JSON.stringify(data);
      this.userDetails = JSON.parse(allData);
      this.userDetails.username;
    });

    return this.isOwner = OwnerPost == User;

  }
*/
