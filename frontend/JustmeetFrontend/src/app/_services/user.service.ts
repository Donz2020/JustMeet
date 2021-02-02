import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {headerGenerator} from "../utils/headerRequest/headerGenerator";
import {pathManager} from "../utils/connection/pathManager";


const BACKEND_URL = new pathManager().getUrl();

const API_URL = BACKEND_URL + '/api/usr/';

const httpOptions = new headerGenerator().getHeader();


@Injectable({
  providedIn: 'root'
})

export class UserService {

  constructor(private http: HttpClient) {
  }

  getUserDetails(): Observable<any> {
    return this.http.get(API_URL + 'getDetails', httpOptions);
  }

  /*
    getUserDetailsPhy(): Observable<any> {
      return this.http.get(API_URL + 'getDetailsPhy', {responseType: 'json'});
    }
  */

  setUserPass(pass): Observable<any> {
    return this.http.patch(API_URL + 'setPass/' + pass, null, httpOptions);
  }

  deleteAcc(): Observable<any> {
    return this.http.post(API_URL + 'delete', null, httpOptions);
  }

}
