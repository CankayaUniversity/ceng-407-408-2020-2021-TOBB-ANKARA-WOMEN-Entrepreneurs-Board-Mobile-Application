import {BaseApi} from '../../base/base.api';
import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Product} from './product.model';

@Injectable()
export class ProductApi extends BaseApi<Product> {
  protected readonly apiUrl: string = '/product';

  constructor(http: HttpClient) {
    super(http);

    /*this.getAll().toPromise();
    this.get(2).toPromise();
    this.create({});
    this.update(2, {});
    this.delete(2);*/
  }
}
