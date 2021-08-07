import { Component, Input, OnInit } from '@angular/core';
import { Search } from '../models/search-result';

@Component({
  selector: 'app-search-result',
  templateUrl: './search-result.component.html',
  styleUrls: ['./search-result.component.css']
})
export class SearchResultComponent implements OnInit {

  @Input() searchResult: any;
  isStockAvailable = true;
  stockDetail:any;

  ELEMENT_DATA: Search[] = [
    {stock: "", date: 'Hydrogen', time: '1.0079'},

  ];

  dataSource:any[];

  displayedColumns: string[] = ['stockPrice', 'stockDate', 'stocktime'];
  

  constructor() { }

  ngOnInit(): void {
    console.log('Search result: '+JSON.stringify(this.searchResult))
    if(this.searchResult!=null && this.searchResult.length>0){
      this.isStockAvailable = true;
      this.stockDetail=this.searchResult[0];
      console.log(this.stockDetail);
      this.dataSource = this.searchResult;
    }else{
      this.isStockAvailable = false;
    }
  }

}
