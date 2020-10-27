import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const API_URL = 'http://localhost:8080/api/usr';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  getPublicContent(): Observable<any> {
    return this.http.get(API_URL + 'all', { responseType: 'json' });
  }

  getUserBoard(): Observable<any> {
    return this.http.get(API_URL + 'usr', { responseType: 'json' });
  }

  getModeratorBoard(): Observable<any> {
    return this.http.get(API_URL + 'mod', { responseType: 'json' });
  }

  getAdminBoard(): Observable<any> {
    return this.http.get(API_URL + 'admin', { responseType: 'json' });
  }

  getUserDetails(): Observable<any> {
    return this.http.get(API_URL + '/getDetailsPhy', { responseType: 'json' });
  }

}
