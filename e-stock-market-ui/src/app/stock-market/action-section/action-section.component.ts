import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog'
import { RegisterPopupComponent } from '../register-popup/register-popup.component'
import { Company } from '../models/company';
import { StockMarketService } from '../services/stock-market.service'
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-action-section',
  templateUrl: './action-section.component.html',
  styleUrls: ['./action-section.component.css']
})
export class ActionSectionComponent implements OnInit {

  searchCompanyCode: string;
  searchResult:String[];

  constructor(
    public dialog: MatDialog,
    private service : StockMarketService,
    private snackBar :MatSnackBar
  ) { }

  ngOnInit(): void {
  }

   searchCompanies(): any {
    var companyDetails = this.service.getCompanyDetails(this.searchCompanyCode).subscribe(data =>{
      this.searchResult=data;
    },(error)=>{
        console.error(error);
    });
    console.log(this.searchResult);
  }

  registerCompany(): void{
    const dialogRef= this.dialog.open(RegisterPopupComponent,{
      height:'auto',
      maxHeight:'90vh',
      width:'20vw',
      panelClass:'register-popup',
      autoFocus:false
    }).afterClosed().subscribe(data=>{
        
    });
  }

  listAllCompanies(): any {
    this.service.getAllCompanyDetails().subscribe(data =>{
      this.searchResult=data;
    },(error)=>{
        console.error(error);
    });
    console.log(this.searchResult);
  }

}
