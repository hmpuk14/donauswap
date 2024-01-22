import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

// anzeigen des DCoin Saldos aus der Datenbank - Änderung Dcoin Saldo in der Datenbank (nach Dcoin-Kauf oder Tausch)
@Injectable({
  providedIn: 'root'
})
export class DonaucoinService {
  private baseUrl = 'http://localhost:8080/api/users'; // Basis-URL angepasst

  constructor(private http: HttpClient) {}
    getSaldo(userId: number): Observable<number> {
    const url = `${this.baseUrl}/${userId}/saldo`;
    return this.http.get<number>(url);
  }

  updateSaldo(userId: number, newSaldo: number): Observable<any> {
    const url = `${this.baseUrl}/${userId}/saldo`;
    return this.http.put(url, newSaldo);
  }
}
