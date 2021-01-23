import {Component, OnInit} from '@angular/core';
import {postService} from "../_services/post.service";
import {TokenStorageService} from "../_services/token-storage.service";
import {postPayload} from "../utils/postPayloads/postPayload";
import {FormControl, FormGroup} from "@angular/forms";
import {ModalService} from "../_modal";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  currentUser: string = null;
  postPayload: Array<postPayload>;
  noPosts: boolean= false;
  newPostform : FormGroup;
  submitted : boolean = false;

  constructor(private token: TokenStorageService,
              private postService: postService,
              private modalService: ModalService) {
  }

  ngOnInit() {
    this.initPostForm();
    this.getUser();
    if (this.currentUser != null){
      this.getAllPosts();
    }
  }

  getUser(){
    this.currentUser = this.token.getUser();
  }

  getAllPosts(){
    if (this.currentUser != null){
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

  openModal(){
    this.initPostForm();
    this.modalService.open('postModal');
  }

  initPostForm(){
    this.newPostform = new FormGroup({
      title: new FormControl(''),
      date: new FormControl(''),
      latitude: new FormControl(''),
      longitude: new FormControl(''),
      free: new FormControl(true),
      description: new FormControl(''),
    });
  }

  // Modal Methods
  createPost(){

  }

  keyDownFunction(event){
    this.submitted = false;
    if (event.keyCode === 13) {
      this.submitted = true;
      this.newPostform.validator;
      if (this.newPostform.validator) {
        this.createPost();
      }
    }
  }

}
