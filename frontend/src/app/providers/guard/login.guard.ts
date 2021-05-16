import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from '@angular/router';
import {AuthService} from '../service/auth.service';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {User} from '../model/user/user.model';
import {Injectable} from '@angular/core';

@Injectable()
export class LoginGuard implements CanActivate {

  constructor(private authService: AuthService, private router: Router, private http: HttpClient) {
  }

  async canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Promise<boolean | UrlTree> {
    const credential = this.authService.getCredential().value;
    if (credential) {
      try {
        const user = await this.http.get<User>(environment.apiUrl + '/api/user/profile').toPromise();
        this.authService.setUser(user);
        return true;
      } catch (e) {
      }
    }
    this.router.navigate(['/login']);
    return false;
  }

}
