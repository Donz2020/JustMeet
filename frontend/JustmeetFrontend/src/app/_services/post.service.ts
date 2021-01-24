import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

const API_URL = 'http://localhost:8080/api/post/';
const httpOptions = {headers: new HttpHeaders({'Content-Type': 'application/json'})};

@Injectable({
  providedIn: 'root'
})

export class postService {

  constructor(private http: HttpClient) {
  }

  //Get all JustMeet Posts
  getPosts(): Observable<any> {
    return this.http.get(API_URL + 'getPosts', {responseType: 'json'});
  }

  //Get Post for ID
  getPost(id): Observable<any> {
    return this.http.get(API_URL + 'getPost/' + id, {responseType: 'json'});
  }

  getMyPosts(): Observable<any> {
    return this.http.get(API_URL + 'getMyPosts' , {responseType: 'json'});
  }

  subscribePost(id): Observable<any> {
    return this.http.post(API_URL + 'subscribe/' + id , {responseType: 'json'});
  }

  deleteSubPost(id): Observable<any> {
    return this.http.post(API_URL + 'delete/' + id + '/subscriber' , {responseType: 'json'});
  }

  createPost(post): Observable<any>{
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
    return this.http.post(API_URL + 'delete/' + id, {responseType: 'json'});
  }

  getOwnerPost(id): Observable<any> {
    return this.http.get(API_URL + 'getPost/' + id + '/owner',{responseType: 'json'});
  }
}
