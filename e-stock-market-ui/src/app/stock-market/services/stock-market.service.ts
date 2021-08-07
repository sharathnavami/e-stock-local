import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { map, catchError } from 'rxjs/operators';
import { Company } from '../models/company'
import { environment } from '../../../environments/environment'

@Injectable({
  providedIn: 'root'
})
export class StockMarketService {

  constructor(private http: HttpClient) { }


  headers= new HttpHeaders()
  .set('Access-Control-Allow-Origin', '*')
  .set('Content-Type', 'application/json')
  .set('Accept', 'application/json');

  public getAllCompanyDetails() : Observable<any>{
    return this.http.get<any>(`${environment.apiHost}${environment.portCompany}${environment.apiCommonURL}${environment.apiCompany}/getall`,{ 'headers': this.headers }).pipe(
      map(data=>{
        return data;
      }),
      catchError((error:any)=>{
        return throwError(error);
      })
    );
  }

  public registerCompanyDetails(payload:any) : Observable<any>{
    return this.http.post<any>(`${environment.apiHost}${environment.portCompany}${environment.apiCommonURL}${environment.apiCompany}/register`,payload,{ 'headers': this.headers }).pipe(
      map(data$=>{
        return data$;
      }),
      catchError((error:any)=>{
        console.log(error)
        return throwError(error);
      })
    );
  }

  public getCompanyDetails(companyCode:string) : Observable<any>{
    var params = new HttpParams().set('companyCode',companyCode);
    var URL=`${environment.apiHost}${environment.portCompany}${environment.apiCommonURL}${environment.apiCompany}/info/${companyCode}`;
    return this.http.get<any>(URL,{ 'headers': this.headers }).pipe(
      map(data$=>{
        return data$;
      }),
      catchError((error:any)=>{
        return throwError(error);
      })
    );
  }

  public getStockDetails(companyCode:string,fromDate:string,toDate:string) : Observable<any>{
    var URL=`${environment.apiHost}${environment.portStock}${environment.apiCommonURL}${environment.apiStocks}/get/${companyCode}/${fromDate}/${toDate}`;
    return this.http.get<any>(URL,{ 'headers': this.headers }).pipe(
      map(data$=>{
       return data$;
      }),
      catchError((error:any)=>{
        return throwError(error);
      })
    );
  }

  public addStocktoCompany(companyCode:string,payload:any) : Observable<any>{
    return this.http.post<any>(`${environment.apiHost}${environment.portStock}${environment.apiCommonURL}${environment.apiStocks}/add/${companyCode}`,payload,{ 'headers': this.headers }).pipe(
      map(data$=>{
        return data$;
      }),
      catchError((error:any)=>{
        return throwError(error);
      })
    );
  }

  public deleteCompany(companyCode:string) : Observable<any>{
    return this.http.delete<any>(`${environment.apiHost}${environment.portCompany}${environment.apiCommonURL}${environment.apiCompany}/delete/${companyCode}`,{ 'headers': this.headers }).pipe(
      map(data$=>{
        return data$;
      }),
      catchError((error:any)=>{
        return throwError(error);
      })
    );
  }
  
}
