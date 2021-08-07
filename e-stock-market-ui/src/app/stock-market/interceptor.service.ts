import { Injectable } from '@angular/core';
import{
    HttpRequest,HttpHandler,HttpEvent,HttpInterceptor
} from '@angular/common/http';

import { Observable } from 'rxjs';

import { environment } from '../../environments/environment';

@Injectable()
export class TokenInterceptor implements HttpInterceptor{

    constructor(){

    }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>>{
        console.log('In intercept');
        request=request.clone({
            setHeaders:{
                Authorization:`Bearer ${environment.apiToken}`
            }
        });
        return next.handle(request);
    }
}
