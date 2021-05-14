import {BaseApi} from '../../base/base.api';
import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Message} from './message.model';
import {environment} from '../../../../environments/environment';

@Injectable()
export class MessageApi extends BaseApi<Message> {
  protected readonly apiUrl: string = '/message';

  constructor(http: HttpClient) {
    super(http);

    /*this.getAll().toPromise();
    this.get(2).toPromise();
    this.create({});
    this.update(2, {});
    this.delete(2);*/
  }

  updateMyProfile(message: Message) {
    return this.http.put<Message>(environment.apiUrl + '/api/message', message);
  }
}
