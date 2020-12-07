import { Component, OnInit } from '@angular/core';
import { UserService } from '../_services/user.service';
import {TokenStorageService} from "../_services/token-storage.service";
import {utilsPayload} from "../utils/utils.component";

@Component({
  selector: 'app-board-admin',
  templateUrl: './board-admin.component.html',
  styleUrls: ['./board-admin.component.scss']
})
export class BoardAdminComponent implements OnInit {
  userDetails: utilsPayload;
  currentUser: string;


constructor(private token: TokenStorageService, private userService: UserService) { }

ngOnInit() {
  let allData: string;
  this.currentUser = this.token.getUser();
  this.userService.getUserDetails().subscribe((data : utilsPayload)=>{
    allData = JSON.stringify(data);
    this.userDetails = JSON.parse(allData);
  });
}

}
