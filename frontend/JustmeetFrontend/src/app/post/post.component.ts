import {Component, OnInit} from '@angular/core';
import {AppComponent} from "../app.component";
import {ActivatedRoute, Router} from "@angular/router";
import {TokenStorageService} from "../_services/token-storage.service";
import {UserService} from "../_services/user.service";
import {postService} from "../_services/post.service";
import {postPayload} from "../utils/postPayloads/postPayload";
import {Location} from '@angular/common';


@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.scss']
})


export class PostComponent implements OnInit {
  postPayload: postPayload;
  currentUser: string;
  errorMessage: string;


  constructor(private token: TokenStorageService,
              private logoutComponent: AppComponent,
              private route: ActivatedRoute,
              private postService: postService,
              private location: Location
  ) {

  }

  ngOnInit() {
    this.getUser();
    this.route.params.subscribe(
      params => {
        let id = params['id'];
        if (id) {
          this.getPostDetail(id);
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

  back() {
    this.location.back();
  }


}
