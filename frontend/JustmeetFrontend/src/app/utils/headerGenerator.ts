import {TokenStorageService} from "../_services/token-storage.service";
import {HttpHeaders} from "@angular/common/http";


const TOKEN_HEADER_KEY = 'Authorization';
const TOKEN_VALUE = 'Bearer ';

export class headerGenerator {

  token:TokenStorageService = new TokenStorageService();
  http: { headers: HttpHeaders } = {headers: new HttpHeaders().append(TOKEN_HEADER_KEY , TOKEN_VALUE + this.token.getToken())};


  public getHeader(){
    return this.http;
  }

}

