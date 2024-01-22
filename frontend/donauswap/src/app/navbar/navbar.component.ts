import { Component } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router'; // Importieren des Router-Services

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {
  private homeVisibleUrls: string[] = ['/handeln', '/donaucoin', '/tauschen', '/kategorie'];

  constructor(public authService: AuthService, private router: Router) {}

  logout() {
    this.authService.logout();
  }
// Verbergen bestimmmter Menüpunkte, wenn man auf der entsprechenden Seite ist

  isHomeVisible(): boolean {
      return this.homeVisibleUrls.some(url => this.router.url.startsWith(url));
  }

  isCurrentRoute(routePath: string): boolean {
    return this.router.url === routePath;
  }
}
