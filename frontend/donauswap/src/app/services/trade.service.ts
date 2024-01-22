import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

// Service zur Unterstützung der Backend Kommunikation, die Korrekte Ablage der Bilder betreffend (Ordner außerhalb der Applikation)
@Injectable({
  providedIn: 'root'
})
export class TradeService {
  private apiUrl = 'http://localhost:8080/api/images';

  constructor(private http: HttpClient) {}

  uploadImages(formData: FormData): Observable<any> {
    return this.http.post(this.apiUrl, formData);
  }
}
