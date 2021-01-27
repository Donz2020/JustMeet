import { Injectable } from '@angular/core';

const TOKEN_KEY = 'accessToken';
const USER_KEY = 'Authorization';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {

  constructor() { }

  signOut() {
    window.sessionStorage.clear();
  }

  public saveToken(token: string) {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token);
  }

  public getToken(): string {
    return window.sessionStorage.getItem(TOKEN_KEY);
  }

  public saveUser(user) {
    window.sessionStorage.removeItem(USER_KEY);
    window.sessionStorage.setItem(USER_KEY, JSON.stringify(user));
    window.sessionStorage.removeItem(USER_KEY);
  }

  public getUser(): string {
    return window.sessionStorage.getItem(TOKEN_KEY);
  }
}
