import {BaseApi} from '../../base/base.api';
import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {News} from './feed.model';

@Injectable()
export class FeedApi extends BaseApi<News>{
  protected readonly apiUrl: string = '/api/feed';
  constructor(http: HttpClient) {
    super(http);
  }
}
