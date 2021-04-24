import {Component, OnInit} from '@angular/core';
import {postService} from "../_services/post.service";
import {TokenStorageService} from "../_services/token-storage.service";
import {postPayload} from "../utils/postPayloads/postPayload";
import {FormControl, FormGroup} from "@angular/forms";
import {ModalService} from "../_modal";
import {newPostPaylod} from "../utils/postPayloads/newPostPaylod";
import {locationResponsePayload} from "../utils/postPayloads/locationResponsePayload";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  currentUser: string = null;
  postPayload: Array<postPayload>;
  noPosts: boolean = false;
  newPostform: FormGroup;
  submitted: boolean = false;
  responseLocation: locationResponsePayload;
  validLocation : boolean = true;

  constructor(private token: TokenStorageService,
              private postService: postService,
              private modalService: ModalService) {
  }

  ngOnInit() {
    this.resetSubmitted();
    this.initPostForm();
    this.getUser();
    if (this.currentUser != null) {
      this.getAllPosts();
    }
  }

  setSubmitted() {
    this.submitted = true;
  }

  resetSubmitted() {
    this.submitted = false;
  }

  setValidLocation(){
    this.validLocation = true;
  }

  getUser() {
    this.currentUser = this.token.getUser();
  }

  getAllPosts() {
    if (this.currentUser != null) {
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

  initPostForm() {
    this.newPostform = new FormGroup({
      title: new FormControl(''),
      date: new FormControl(''),
      civic: new FormControl(''),
      street: new FormControl(''),
      city: new FormControl(''),
      type: new FormControl(''),
      free: new FormControl(true),
      description: new FormControl(''),
    });
  }

  // Modal Methods
  openModal() {
    this.setValidLocation();
    this.initPostForm();
    this.modalService.open('postModal');
  }

  closeModal() {
    this.modalService.close("postModal");
  }

  createPost() {
    this.setSubmitted();
    if (this.newPostform.valid) {
      let allData;
      this.postService.getLocationGeo(
        this.newPostform.get('civic').value,
        this.newPostform.get('street').value,
        this.newPostform.get('city').value).subscribe((data) => {
        allData = JSON.stringify(data);
        this.responseLocation = JSON.parse(allData);
        if (this.responseLocation.status == 'OK'){
          let lat: any = this.responseLocation.results[0].geometry.location.lat;
          let lng: any = this.responseLocation.results[0].geometry.location.lng;
          let newPostPayloadData: newPostPaylod = {
            title: this.newPostform.get('title').value,
            date: this.newPostform.get('date').value,
            latitude: <number>lat,
            longitude: <number>lng,
            descriptionType: this.newPostform.get('type').value,
            descriptionFree: this.newPostform.get('free').value,
            descriptionText: this.newPostform.get('description').value,
          }
          this.postService.createPost(newPostPayloadData).subscribe();
          this.closeModal();
          setTimeout(function () {
            window.location.href = "/home"
          }, 500);
        } else {
          this.validLocation = false;
        }
      });
    }
  }

  keyDownFunction(event) {
    this.resetSubmitted();
    this.setValidLocation();
    if (event.keyCode == 13) {
      this.setSubmitted();
      if (this.newPostform.valid) {
        this.createPost();
      }
    }
  }
}
