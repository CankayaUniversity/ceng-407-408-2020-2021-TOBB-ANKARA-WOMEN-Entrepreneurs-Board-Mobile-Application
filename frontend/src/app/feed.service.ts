import { Injectable } from '@angular/core';
import { environment } from '../environments/environment';
import { HttpClient } from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class FeedService {
  currentNews: any;
  constructor(private http: HttpClient) {}

  getFeed(apiUrl: string){
    return this.http.get(`${environment.apiUrl}/api/feed`);
  }
}
