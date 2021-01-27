import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';
import {headerGenerator} from "../utils/headerGenerator";
import {pathManager} from "../utils/pathManager";


const BACKEND_URL = new pathManager().getUrl();

const API_URL = BACKEND_URL+'/api/admin/';

const httpOptions = new headerGenerator().getHeader();


@Injectable({
  providedIn: 'root'
})

export class AdminService {

  constructor(private http: HttpClient) {
  }

  changeUserPass(idPayload): Observable<any> {
    return this.http.post(API_URL + 'setPass' , {
      username: idPayload.username,
      password: idPayload.password,
    },httpOptions);
  }

  changeUserRole(payload): Observable<any> {
    return this.http.post(API_URL + 'setRole' , {
      username: payload.username,
      roles: payload.roles,
    },httpOptions);
  }

  changeUserStatus(payload): Observable<any> {
    return this.http.post(API_URL + 'setStatus' , {
      username: payload.username,
      active: payload.active,
    },httpOptions);
  }

  deleteUserAccount(payload): Observable<any> {
    return this.http.post(API_URL + 'delete' , {
      username: payload.username,
    },httpOptions);
  }

}
