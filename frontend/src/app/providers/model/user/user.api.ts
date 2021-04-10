import {BaseApi} from '../../base/base.api';
import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {User} from './user.model';

@Injectable()
export class UserApi extends BaseApi<User> {
  protected readonly apiUrl: string = '/user';

  constructor(http: HttpClient) {
    super(http);

    /*this.getAll().toPromise();
    this.get(2).toPromise();
    this.create({});
    this.update(2, {});
    this.delete(2);*/
  }
}
