import {Component, OnInit} from '@angular/core';
import {UserService} from '../_services/user.service';
import {TokenStorageService} from "../_services/token-storage.service";
import {profilePayload} from "../utils/profilePayloads/profilePayload";
import {Router} from "@angular/router";
import {AdminService} from "../_services/admin.service";
import {FormControl, FormGroup} from "@angular/forms";
import {idPayload} from "../utils/registerPayloads/identificatorPayload";
import {changeUserRolePayload} from "../utils/profilePayloads/changeUserRolePayload";
import {activePayload} from "../utils/profilePayloads/activePayload";
import {deletePayload} from "../utils/profilePayloads/deletePayload";

@Component({
  selector: 'app-board-admin',
  templateUrl: './board-admin.component.html',
  styleUrls: ['./board-admin.component.scss']
})
export class BoardAdminComponent implements OnInit {
  userDetails: profilePayload;
  changeRolePayload: changeUserRolePayload;
  currentUser: string;
  formActive: FormGroup;
  formEmail: FormGroup;
  formPass: FormGroup;
  formRole: FormGroup;
  checkAdmin: boolean = false;
  checkMod: boolean = false;


  constructor(private token: TokenStorageService, private userService: UserService, private router: Router, private adminService: AdminService) {
  }

  ngOnInit() {
    let allData: string;
    this.currentUser = this.token.getUser();
    this.userService.getUserDetails().subscribe((data: profilePayload) => {
      allData = JSON.stringify(data);
      this.userDetails = JSON.parse(allData);
      for (let i of this.userDetails.roles) {
        if (i == "ADMIN") {
          this.checkAdmin = true;
        }
        if (i == "MOD") {
          this.checkMod = true;
        }
      }

    });

    this.initEmail();
    this.initPass();
    this.initRole();
    this.initActive();
  }

  initEmail(): void {
    this.formEmail = new FormGroup({
      email: new FormControl(''),
    });
  }

  initPass(): void {
    this.formPass = new FormGroup({
      pass: new FormControl(''),
    });
  }

  initRole(): void {
    this.formRole = new FormGroup({
      roles: new FormControl(''),
    });
  }

  initActive(): void {
    this.formActive = new FormGroup({
      active: new FormControl(''),
    });
  }


  changeUserPass() {
    if (this.formEmail.valid) {
      let idPayload: { password: string; username: string } = {
        username: this.formEmail.get("email").value,
        password: this.formPass.get("pass").value,
      };
      this.adminService.changeUserPass(idPayload).subscribe(
        (data: idPayload) => {
          //data = idPayload;
          // todo cambiare payload nel caso
        });
    }
  }


  changeUserRole() {
    let allData: string;

    let role = [this.formRole.get('roles').value];

    if (this.formEmail.valid) {
      let changeUserRolePayload: changeUserRolePayload = {
        username: this.formEmail.get('email').value,
        roles: role

      };

      this.adminService.changeUserRole(changeUserRolePayload).subscribe(
        (data: changeUserRolePayload) => {
          allData = JSON.stringify(data);

          this.changeRolePayload = JSON.parse(allData);
          data = changeUserRolePayload;


        });
    }

  }

  changeUserActive() {
    if (this.formEmail.valid) {
      let activePayload: activePayload = {
        username: this.formEmail.get("email").value,
        active: this.formActive.get("active").value,
      };
      this.adminService.changeUserStatus(activePayload).subscribe(
        (data: activePayload) => {
          data = activePayload;

        });
    }
  }


  deleteUserAccount() {
    if (this.formEmail.valid) {
      let deletePayload: deletePayload = {
        username: this.formEmail.get("email").value,

      };
      if (confirm("Are you sure ?")) {
        this.adminService.deleteUserAccount(deletePayload).subscribe(
          (data: deletePayload) => {
            data = deletePayload;

          });
      }
    }
  }

}
