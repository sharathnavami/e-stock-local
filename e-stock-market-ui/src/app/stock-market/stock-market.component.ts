import { Component, OnInit } from '@angular/core';
import { AuthGuardServiceService } from './services/auth-guard-service.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-stock-market',
  templateUrl: './stock-market.component.html',
  styleUrls: ['./stock-market.component.css']
})
export class StockMarketComponent implements OnInit {


  constructor(
     private authService: AuthGuardServiceService,
     private router: Router
    ){}

  ngOnInit(): void {

  }

  logout(){
    this.authService.deleteToken();
    this.router.navigate(['login']);
  }

}
 