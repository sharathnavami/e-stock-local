import { Component, OnInit } from '@angular/core';
import { DatePipe } from'@angular/common';
import { Event } from '@angular/router';
import { StockMarketService } from '../services/stock-market.service'
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-filter-section',
  templateUrl: './filter-section.component.html',
  styleUrls: ['./filter-section.component.css']
})
export class FilterSectionComponent implements OnInit {

  companyList:any;
  stockDetails;
  fromDate:string;
  toDate:string;
  companyCode:string;
  validCompanyName:boolean=true;
  validFromDate:boolean=true;
  validToDate:boolean=true;
  stockPrice:any;
  showAddStock:boolean=false;

  constructor(
    private service:StockMarketService, 
    private datePipe:DatePipe,
    private snackBar :MatSnackBar
    ) { }

  ngOnInit(): void {
    this.listAllCompanies();
  }

  listAllCompanies(): any {
    this.service.getAllCompanyDetails().subscribe(data =>{
      this.companyList=data;
    },(error)=>{
        console.error(error);
    });
    console.log('list :'+this.companyList);
  }

  fetchCompanyDetails(companyCode){
    this.service.getAllCompanyDetails().subscribe(data =>{
      this.companyList=data;
      console.log(JSON.stringify(data));
    },(error)=>{
      console.error(error);
    });
  }

  fetchStockDetails(){
    var valid=true;
    this.validFromDate=true;
    this.validToDate=true;
    this.validCompanyName=true;

    if(this.fromDate === undefined || this.fromDate === null ||this.fromDate === ''){
      this.validFromDate=false;
      valid=false;
    }
    if(this.toDate === undefined || this.toDate === null ||this.toDate === ''){
      this.validToDate=false;
      valid=false;
    }
    if(this.companyCode === undefined || this.companyCode === null || this.companyCode === ''){
      this.validCompanyName=false;
      valid=false;
    }
    if(valid){
      var dateFrom=this.datePipe.transform(this.fromDate,'yyyy-MM-dd');
      var dateTo=this.datePipe.transform(this.toDate,'yyyy-MM-dd');
      this.service.getStockDetails(this.companyCode,dateFrom,dateTo).subscribe(data =>{
        this.stockDetails=data;
        console.log(JSON.stringify(data));
      },(error)=>{
        console.error(error);
      });
    }
  }

  addStockToCompany():void {
    const payoad = {
      "stockPrice" : this.stockPrice
    };
    this.service.addStocktoCompany(this.companyCode,payoad).subscribe(data =>{
        console.log("Stock Added")
        this.snackBar.open('Stock Added Successfully','', {
          "duration" : 2000,
       });
    },(error)=>{
        console.error(error);
        this.snackBar.open('Error Occured while adding stock','', {
          "duration" : 2000,
       });
    });
    this.showAddStock=false;
    this.stockPrice="";
  }

  deleteCompany():void {
    this.service.deleteCompany(this.companyCode).subscribe(data =>{
        console.log("Company Deleted")
        this.snackBar.open('Company Deleted Successfully','', {
          "duration" : 2000,
       });
    },(error)=>{
        console.error(error);
    });
  }

  showAddStockFn():void{
    this.showAddStock=true;
  }

  hideAddStockFn():void{
    this.showAddStock=false;
  }

  refresh(){
    this.listAllCompanies();
  }

  resetSearchResult(){
    var emptyValue;
    this.stockDetails=emptyValue;
    this.fromDate=emptyValue;
    this.toDate=emptyValue;
    this.companyCode=emptyValue;
    var valid=true;
    this.validFromDate=true;
    this.validToDate=true;
    this.validCompanyName=true;
  }

}
