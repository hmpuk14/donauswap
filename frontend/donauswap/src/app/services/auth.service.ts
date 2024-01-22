import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of, BehaviorSubject, Subject } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { User } from '../models/user.model';

// Service zur Übergabe von Registrierungs und Login Info an das Backend
@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private currentUserSubject: BehaviorSubject<User | null>;
  public currentUserObservable: Observable<User | null>;
  private loginUrl = 'http://localhost:8080/api/users/login';
  private registerUrl = 'http://localhost:8080/api/users/register';
  private timeoutId: any;
  private inactivityTimeout = 3600000; // 1 Stunde in Millisekunden - eingeloggt bleiben
  private userStatusChanged = new Subject<void>(); // Benutzerstatus-Änderung

  constructor(private http: HttpClient) {
    this.currentUserSubject = new BehaviorSubject<User | null>(null);
    this.currentUserObservable = this.currentUserSubject.asObservable();
    this.checkLoggedInStatus();
  }

  login(email: string, password: string): Observable<any> {
    return this.http.post<User>(this.loginUrl, { email, password })
      .pipe(
        catchError(this.handleError<any>('login', null)),
        tap((user: User) => {
          if (user) {
            this.currentUserSubject.next(user);
            localStorage.setItem('currentUser', JSON.stringify(user));
            this.resetTimer();
            this.userStatusChanged.next(); // Benutzerstatus-Änderung
          }
        })
      );
  }

  logout() {
    clearTimeout(this.timeoutId);
    localStorage.removeItem('currentUser');
    this.currentUserSubject.next(null);
    this.userStatusChanged.next(); // Benutzerstatus-Änderung
  }

  resetTimer() {
    clearTimeout(this.timeoutId);
    this.timeoutId = setTimeout(() => this.logout(), this.inactivityTimeout);
  }

  getCurrentUser(): User | null {
    return this.currentUserSubject.value;
  }

  getIsLoggedIn(): boolean {
    return this.currentUserSubject.value !== null;
  }

  register(user: User): Observable<any> {
    return this.http.post(this.registerUrl, user);
  }

  getUserStatusListener() {
    return this.userStatusChanged.asObservable();
  }

  private checkLoggedInStatus() {
    const userJson = localStorage.getItem('currentUser');
    if (userJson) {
      const user: User = JSON.parse(userJson);
      this.currentUserSubject.next(user);
      this.resetTimer();
    } else {
      this.currentUserSubject.next(null);
    }
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(`${operation} failed: ${error.message}`);
      return of(result as T);
    };
  }
}
