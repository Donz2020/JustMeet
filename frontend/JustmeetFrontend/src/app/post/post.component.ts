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


@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.scss']
})


export class PostComponent implements OnInit {
  postPayload: postPayload;
  currentUser: string;
  errorMessage: string;
  isOwner = false;
  userDetails: changeUserRolePayload;
  id : number = null;
  user : string


  //latitude = 43.439445;
  //longitude = 13.65975;
  //googleMapType = 'hybrid';

  constructor(private token: TokenStorageService,
              private logoutComponent: AppComponent,
              private route: ActivatedRoute,
              private postService: postService,
              private location: Location,
              private userService: UserService
  ) {

  }

  ngOnInit() {
    this.getUser();
    this.route.params.subscribe(
      params => {
        this.id = params['id'];
        if (this.id != null) {
          this.getPostDetail(this.id);
          this.checkOwner();

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

  reloadPage() {
    window.location.reload();
  }


  async checkOwner() : Promise<boolean> {
    let allData : string;
    let Owner : string;

    //this.currentUser = this.token.getUser();

    //todo filtrare response con ownername

    let OwnerPost = await this.postService.getPost(this.id).subscribe((data : postPayload) => {
      Owner = JSON.stringify(data);
      //this.postPayload.ownerName = JSON.parse(Owner);
    });


    let User = await this.userService.getUserDetails().subscribe((data: profilePayload) => {
      allData = JSON.stringify(data);
      this.userDetails = JSON.parse(allData);
      this.userDetails.username;
    });

    return this.isOwner = OwnerPost == User;

  }


  back() {
    this.location.back();
  }

  getLocation() {
    const loc = this.postPayload.location;
    alert(loc);
  }


  chunkArray() {
    const array = this.postPayload.location;

    const char = array.splice(Number(","), 0);

    alert(char);


  }


}
