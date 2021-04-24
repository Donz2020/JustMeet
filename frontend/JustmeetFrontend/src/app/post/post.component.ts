import {Component, OnInit} from '@angular/core';
import {AppComponent} from "../app.component";
import {ActivatedRoute, Router} from "@angular/router";
import {TokenStorageService} from "../_services/token-storage.service";
import {UserService} from "../_services/user.service";
import {postService} from "../_services/post.service";
import {postPayload} from "../utils/postPayloads/postPayload";
import {Location} from '@angular/common';
import {profilePayload} from "../utils/profilePayloads/profilePayload";


@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.scss']
})


export class PostComponent implements OnInit {
  postPayload: postPayload;
  userDetailsPayload: profilePayload;
  currentUser: string;
  errorMessage: string;
  isOwner = false;
  id: number = null;
  user: string


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
    this.getCurrentUser();
    this.route.params.subscribe(
      params => {
        this.id = params['id'];
        if (this.id != null) {
          this.getPostDetail(this.id);
        }
      });
  }


  getUser() {
    this.currentUser = null;
    this.currentUser = this.token.getUser();
  }

  getPostDetail(id: number) {
    this.postService.getPost(id)
      .subscribe(
        response => this.postPayload = response,
        error => this.errorMessage = <any>error);
  }

  subscribeToPost(id: number) {
    this.postService.subscribePost(id).subscribe();
    this.reloadPage();

  }


  deleteSubscribePost(id: number) {
    this.postService.deleteSubPost(id).subscribe();
    this.reloadPage();
  }

  getCurrentUser() {
    let allData: string;
    this.userService.getUserDetails().subscribe((data: profilePayload) => {
      allData = JSON.stringify(data);
      this.userDetailsPayload = JSON.parse(allData);
    });

  }

  checkOwner() {
    return this.postPayload.ownerName == this.userDetailsPayload.username;
  }

  checkSub(): boolean {
    let retValue = false;
    this.postPayload.subscribers.forEach(value => {
      if (value == this.userDetailsPayload.username) {
        retValue = true;
      }
    });
    return retValue;
  }

  checkVRF(): boolean {
    let retValue = true;
    this.userDetailsPayload.roles.forEach(value => {
      if (value == "VRF") {
        retValue = false;
      }
    });
    return retValue;
  }

  deleteMyPost(id) {
    if (this.checkOwner()) {
      this.postService.deletePost(id).subscribe();
      this.isOwner = true;
      setTimeout(function () {
        window.location.href = "/home"
      }, 800);
    } else {
      this.isOwner = false;
    }
  }

  reloadPage() {
    setTimeout(function () {
      window.location.reload()
    }, 1000);
  }

  back() {
    this.location.back();
  }

}
