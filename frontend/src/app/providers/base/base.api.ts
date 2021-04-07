import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../../../environments/environment';

export abstract class BaseApi<T> {
  protected readonly appUrl: string = environment.apiUrl + '/api';
  protected abstract readonly apiUrl: string = '';

  protected getApiUrl(id?: number | string): string {
    return this.appUrl + this.apiUrl + (id ? '/' + id : '');
  }

  protected constructor(protected http: HttpClient) {
  }

  getAll(): Observable<T[]> {
    return this.http.get<T[]>(this.getApiUrl());
  }

  get(id: number | string): Observable<T> {
    return this.http.get<T>(this.getApiUrl(id));
  }

  create(t: T): Observable<T> {
    return this.http.post<T>(this.getApiUrl(), t);
  }

  update(id: number | string, t: T): Observable<T> {
    return this.http.put<T>(this.getApiUrl(id), t);
  }

  delete(id: number | string): Observable<number> {
    return this.http.delete<number>(this.getApiUrl(id));
  }
}
