import {Component, OnInit} from '@angular/core';
import {UserService} from '../_services/user.service';
import {TokenStorageService} from "../_services/token-storage.service";
import {profilePayload} from "../utils/profilePayloads/profilePayload";
import {Router} from "@angular/router";
import {AdminService} from "../_services/admin.service";
import {FormControl, FormGroup} from "@angular/forms";
import {of} from "rxjs";

@Component({
  selector: 'app-board-admin',
  templateUrl: './board-admin.component.html',
  styleUrls: ['./board-admin.component.scss']
})
export class BoardAdminComponent implements OnInit {
  userDetails: profilePayload;
  currentUser: string;
  formAdmin : FormGroup;
  checkAdmin : boolean = false;
  checkMod : boolean = false;


  constructor(private token: TokenStorageService, private userService: UserService, private router: Router, private adminService: AdminService) {
  }

  ngOnInit() {
    let allData: string;
    this.currentUser = this.token.getUser();
    this.userService.getUserDetails().subscribe((data: profilePayload) => {
      allData = JSON.stringify(data);
      this.userDetails = JSON.parse(allData);
      for(let i of this.userDetails.roles){
        if(i == "ADMIN" ){
          this.checkAdmin = true;
        }
        if (i =="MOD"){
          this.checkMod = true;
        }
      }

    });
    this.initForm();
  }

  initForm(): void {

    this.formAdmin = new FormGroup({
      email: new FormControl(''),
      pass: new FormControl(''),
      name: new FormControl(''),
      surname: new FormControl(''),
      birthDate: new FormControl(''),
    });
  }


  deleteAccount() {
    if (confirm("Are you sure ?")) {
      this.adminService.delUserAcc().subscribe();

      this.router.navigateByUrl('/admin');
    }
  }

}
