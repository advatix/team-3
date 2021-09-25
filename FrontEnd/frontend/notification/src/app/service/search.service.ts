import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { SearchData } from '../modules/search/searchData';

@Injectable({
  providedIn: 'root'
})


export class SearchService {
  searchUrl : string ="/search/email";
  constructor(private http: HttpClient) { }


  getSearchdataObs() : Observable<SearchData> {
    return this.http.get<SearchData>(environment.WEBSERVICE_URL+this.searchUrl);
  }

  getSearchData(){
    console.log(environment.WEBSERVICE_URL+this.searchUrl);
    return this.http.get(environment.WEBSERVICE_URL+this.searchUrl);
  }
}
