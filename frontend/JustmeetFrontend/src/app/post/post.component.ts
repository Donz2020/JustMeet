import {Component, OnInit} from '@angular/core';


import {AppComponent} from "../app.component";
import {Router} from "@angular/router";
import {TokenStorageService} from "../_services/token-storage.service";
import {UserService} from "../_services/user.service";
import {ModalService} from "../_modal";



@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.scss']
})


export class PostComponent implements OnInit {


  constructor(private token: TokenStorageService,
              private userService: UserService,
              private logoutComponent: AppComponent,
              private router: Router,
              private modalService: ModalService) {

  }

  ngOnInit() {


  }
}
