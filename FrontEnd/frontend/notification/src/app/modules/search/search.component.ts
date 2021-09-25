import { Component, OnInit } from '@angular/core';
import { SearchService } from 'src/app/service/search.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  searchData: any;
  searchObject: any;
  constructor(private searchService:SearchService) { }

  ngOnInit(): void {
    console.log("in search component");
   this.searchService.getSearchData().subscribe(
      (data) => {
        this.searchData = data;
        this.searchObject=this.searchData.content;
        console.log(this.searchData.content);
      },
      (err) => {
        console.log("err", err);
      }
    );;
   
  }



}
