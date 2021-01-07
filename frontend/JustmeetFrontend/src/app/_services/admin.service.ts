import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import {idPayload} from "../utils/registerPayloads/identificatorPayload";


const API_URL = 'http://localhost:8080/api/admin/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })};

@Injectable({
  providedIn: 'root'
})

export class AdminService {

  constructor(private http: HttpClient) {
  }

  delUserAcc(): Observable<any> {
    return this.http.post(API_URL + 'delete', {responseType: 'json'});
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
      roles: payload.password,
    },httpOptions);
  }

}
