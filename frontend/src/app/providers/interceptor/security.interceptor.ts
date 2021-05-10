import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
import {AuthService} from '../service/auth.service';
import {Injectable} from '@angular/core';

@Injectable()
export class SecurityInterceptor implements HttpInterceptor {

  constructor(private authService: AuthService) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const credential = this.authService.getCredential().value;
    if (credential) {
      req = req.clone({
        setHeaders: {
          Authorization: 'Bearer ' + credential.access_token,
        },
      });
    }
    return next.handle(req);
  }

}
