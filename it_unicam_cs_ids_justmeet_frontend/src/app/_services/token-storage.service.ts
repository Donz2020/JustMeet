import { Injectable } from '@angular/core';

const TOKEN_KEY = 'accessToken';
const USER_KEY = 'Authorization';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {

  constructor() { }

  signOut() {
    localStorage.clear();
  }

  public saveToken(token: string) {
    localStorage.removeItem(TOKEN_KEY);
    localStorage.setItem(TOKEN_KEY, token);
  }

  public getToken(): string {
    return localStorage.getItem(TOKEN_KEY);
  }

  public saveUser(user) {
    localStorage.removeItem(USER_KEY);
    localStorage.setItem(USER_KEY, JSON.stringify(user));
    localStorage.removeItem(USER_KEY);
  }

  public getUser() {
    return localStorage.getItem(TOKEN_KEY);
  }
}
