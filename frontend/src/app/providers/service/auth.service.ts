import {Injectable} from '@angular/core';
import {BehaviorSubject} from 'rxjs';
import {User} from '../model/user/user.model';
import {Credential} from '../model/user/credential.model';

@Injectable()
export class AuthService {

  private credential$ = new BehaviorSubject<Credential>(null);
  private user$ = new BehaviorSubject<User>(null);

  constructor() {
    const credential = localStorage.getItem('credential');
    if (credential) {
      this.credential$.next(JSON.parse(credential));
    }
  }

  setCredential(credential: Credential) {
    if (credential) {
      localStorage.setItem('credential', JSON.stringify(credential));
    } else {
      localStorage.removeItem('credential');
    }
    this.credential$.next(credential);
  }

  getCredential() {
    return this.credential$;
  }

  setUser(user: User) {
    this.user$.next(user);
  }

  getUser() {
    return this.user$;
  }

}
