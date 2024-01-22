import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

// Service zum holen von Informationen zu den Kategorien
@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  private baseUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {}

  getCategories(): Observable<any> {
    return this.http.get(`${this.baseUrl}/categories`).pipe(
      tap(data => console.log('Received categories:', data))
    );
  }
  getOffersByCategory(categoryId: number): Observable<any> {
      return this.http.get(`${this.baseUrl}/categories/${categoryId}/tradeables`).pipe(
        tap(data => console.log(`Received offers for category ${categoryId}:`, data))
      );
    }
  }
