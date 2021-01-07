import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';

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

  changeUserPass(): Observable<any> {
    return this.http.post(API_URL + 'setPass', {responseType: 'json'});
  }

}
