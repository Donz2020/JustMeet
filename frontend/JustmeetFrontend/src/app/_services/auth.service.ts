import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

const AUTH_API = 'http://localhost:8080/api/auth/';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) {
  }

  login(credentials): Observable<any> {
    return this.http.post(AUTH_API + 'login', {
      username: credentials.username,
      password: credentials.password
    }, httpOptions);
  }
/*
  post(user, url): Observable<any> {
    return this.http.post(AUTH_API + url, {
      username: user.username,
      password: user.password,
      name: user.name,
      surname: user.surname,
      birthDate: user.birthDate,
    }, httpOptions);
  }
*/

  register(user): Observable<any> {
    return this.http.post(AUTH_API + 'register', {
      username: user.username,
      password: user.password,
    }, httpOptions);
  }


  registerBusiness(business): Observable<any> {
    return this.http.post(AUTH_API + 'registerBusiness', {
      username: business.username,
      password: business.password,
    }, httpOptions);
  }

}
