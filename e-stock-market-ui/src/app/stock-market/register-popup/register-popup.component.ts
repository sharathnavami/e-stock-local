import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef} from '@angular/material/dialog';
import { Company } from '../models/company';
import { StockMarketService} from '../services/stock-market.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-register-popup',
  templateUrl: './register-popup.component.html',
  styleUrls: ['./register-popup.component.css']
})
export class RegisterPopupComponent implements OnInit {

  formGroup;

  constructor(
    public dialogRef: MatDialogRef<RegisterPopupComponent>,
    private formBuilder: FormBuilder,
    private service:StockMarketService,
    private snackBar :MatSnackBar
    ) { 

      this.formGroup = this.formBuilder.group({
        companyName:  ['', Validators.required ],
        companyCode: ['', Validators.required ],
        companyCEO:  ['', Validators.required ],
        companyTurnover: ['', Validators.required ],
        companyWebsite: ['', Validators.required ],
        stockExchange: ['', Validators.required ]
      });
    }

  ngOnInit(): void {
  }

  closePopup(){
    this.dialogRef.close();
  }

  onSubmit(formData) {
    var companyName = formData['companyName'];

    var stockExchange = formData['stockExchange'];
    console.log(stockExchange);

    var companyTurnover: number = formData['companyTurnover'];
    if(companyTurnover<100000000){
      this.snackBar.open('Company Turnover should be greater than 10CR','', {
        "duration" : 2000,
     });
    }else{
      const payload = {
        "companyName": formData['companyName'],
        "companyCode" : formData['companyCode'],
        "companyCEO":formData['companyCEO'],
        "companyTurnover":formData['companyTurnover'] ,
        "companyWebsite":formData['companyWebsite'] ,
        "stockExchange":formData['stockExchange'] 
      }
      this.service.registerCompanyDetails(payload).subscribe(
        data => {
          console.log(data.message);
          this.closePopup();
          this.snackBar.open('Company Registered','', {
            "duration" : 2000,
         });
        },
        (error:HttpErrorResponse) => 
        {
          this.snackBar.open(error.error,'', {
            "duration" : 2000,
         });
        }
      );
    }
  }

}
