import {Component, OnInit} from '@angular/core';
import {postService} from "../_services/post.service";
import {TokenStorageService} from "../_services/token-storage.service";
import {postPayload} from "../utils/postPayloads/postPayload";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  currentUser: string;
  postPayload: Array<postPayload>;
  noPosts: boolean= false;

  constructor(private token: TokenStorageService, private postService: postService) {
  }

  ngOnInit() {
    this.getUser();
    this.getAllPosts();
  }

  getUser(){
    this.currentUser = "";
    this.currentUser = this.token.getUser();
  }

  getAllPosts(){
    if (this.currentUser != ""){
      this.postService.getPosts().subscribe(
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
