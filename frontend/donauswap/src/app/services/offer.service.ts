import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { TradeData } from '../models/dswap.model';

// zufälliges Anzeigen von Bild und Name von Einzelangeboten, Suchfunktion, Darstellung eines bestimmten Einzelangebots nach id, Übergabe von Infos zum erfolgten Tausch an Backend/Datenbank
@Injectable({
  providedIn: 'root'
})
export class OfferService {
  private apiUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) { }

  getOffers(): Observable<any> {
    return this.http.get(`${this.apiUrl}/offers/random`).pipe(
      tap((data: any) => console.log('Received data:', data)),
      catchError((error: any) => {
        console.error('Error fetching data:', error);
        return throwError(error);
      })
    );
  }

  searchOffers(keyword: string): Observable<any> {
    return this.http.get(`${this.apiUrl}/offers/search`, { params: { keyword: keyword } }).pipe(
      tap((data: any) => console.log('Search results:', data)),
      catchError((error: any) => {
        console.error('Error during search:', error);
        return throwError(error);
      })
    );
  }

  getOfferById(id: string): Observable<any> {
    return this.http.get(`${this.apiUrl}/offers/byid/${id}`).pipe(
      tap(response => console.log(`Received offer details for ID ${id}:`, response)),
      catchError(error => {
        console.error(`Error fetching offer details for ID ${id}:`, error);
        return throwError(error);
      })
    );
  }

  createTrade(tradeData: TradeData): Observable<any> {
    const url = `${this.apiUrl}/offers/trade`;
    return this.http.post(url, tradeData).pipe(
      tap(response => console.log('Trade created:', response)),
      catchError(error => {
        console.error('Error creating trade:', error);
        return throwError(error);
      })
    );
  }
}
