import { NgModule } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';

import { StockMarketRoutingModule } from './stock-market-routing.module';
import { StockMarketComponent } from './stock-market.component';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatSnackBarModule} from '@angular/material/snack-bar'
import { MatDialogModule, MAT_DIALOG_DEFAULT_OPTIONS} from '@angular/material/dialog';
import { MatSelectModule} from '@angular/material/select';
import { RegisterPopupComponent } from './register-popup/register-popup.component';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { StockMarketService } from './services/stock-market.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatTableModule } from '@angular/material/table';
import { FilterSectionComponent } from './filter-section/filter-section.component';
import { SearchResultComponent } from './search-result/search-result.component';
import { ActionSectionComponent } from './action-section/action-section.component'
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { TokenInterceptor } from './interceptor.service';
import { AuthGuardServiceService } from './services/auth-guard-service.service';


@NgModule({
  declarations: [
    StockMarketComponent,
    RegisterPopupComponent,
    FilterSectionComponent,
    SearchResultComponent,
    ActionSectionComponent
  ],
  imports: [
    CommonModule,
    StockMarketRoutingModule,
    MatToolbarModule,
    MatIconModule,
    MatDialogModule,
    MatSelectModule,
    MatDatepickerModule,
    MatNativeDateModule,
    FormsModule,
    ReactiveFormsModule,
    MatTableModule,
    MatSnackBarModule
    
  ],
  providers :[
    RegisterPopupComponent,
    AuthGuardServiceService,
    StockMarketService,
    DatePipe,,{
      provide: HTTP_INTERCEPTORS,
      useClass:TokenInterceptor,
      multi:true
    },
    {provide: MAT_DIALOG_DEFAULT_OPTIONS, useValue: {hasBackdrop: false}}
  
  ]
})
export class StockMarketModule { }
