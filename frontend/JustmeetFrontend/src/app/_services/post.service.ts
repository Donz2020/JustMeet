import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {headerGenerator} from "../utils/headerRequest/headerGenerator";
import {pathManager} from "../utils/connection/pathManager";

const BACKEND_URL = new pathManager().getUrl();

const API_URL = BACKEND_URL + '/api/post/';

const MAPS_URL = 'https://maps.googleapis.com/maps/api/geocode/json?latlng=';
const MAPS_GEO = 'https://maps.googleapis.com/maps/api/geocode/json?address=';
const API_KEY = '&key=AIzaSyCKmNrNcklAL5GKNzQP6CnnohGkeCPk1sc';

const httpOptions = new headerGenerator().getHeader();


@Injectable({
  providedIn: 'root'
})

export class postService {

  constructor(private http: HttpClient) {
  }

  //Get all JustMeet Posts
  getPosts(): Observable<any> {
    return this.http.get(API_URL + 'getPosts', httpOptions);
  }

  //Get Post for ID
  getPost(id): Observable<any> {
    return this.http.get(API_URL + 'getPost/' + id, httpOptions);
  }

  getMyPosts(): Observable<any> {
    return this.http.get(API_URL + 'getMyPosts', httpOptions);
  }

  subscribePost(id): Observable<any> {
    return this.http.post(API_URL + 'subscribe/' + id, null, httpOptions);
  }

  deleteSubPost(id): Observable<any> {
    return this.http.post(API_URL + 'delete/' + id + '/subscriber', null, httpOptions);
  }

  createPost(post): Observable<any> {
    return this.http.post(API_URL + 'add',
      {
        title: post.title,
        date: post.date,
        latitude: post.latitude,
        longitude: post.longitude,
        descriptionType: post.descriptionType,
        descriptionFree: post.descriptionFree,
        descriptionText: post.descriptionText,
      }, httpOptions);
  }

  deletePost(id): Observable<any> {
    return this.http.post(API_URL + 'delete/' + id, null, httpOptions);
  }

  getLocationDetails(lat, long): Observable<any> {
    return this.http.get(MAPS_URL + lat + ',' + long + API_KEY, {responseType: 'json'});
  }

  getLocationGeo(civic, street, city): Observable<any> {
    return this.http.get(MAPS_GEO + civic + street + ',' + city + ',' + API_KEY, {responseType: 'json'});
  }
}
