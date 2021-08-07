import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';

export const TOKEN_NAME:string = 'jwt_token';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardServiceService implements CanActivate{

  constructor(public router: Router) {
   }

   canActivate(){
    if (!!!sessionStorage.getItem(TOKEN_NAME)) {
      this.router.navigate(['login']);
      return false;
    }
    return true;
   }

   setToken(token){
    return sessionStorage.setItem(TOKEN_NAME,token);
  }

  getToken(){
    return sessionStorage.getItem(TOKEN_NAME);
  }

  deleteToken(token?){
    return sessionStorage.removeItem(TOKEN_NAME);
  }

}
