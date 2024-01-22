import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { OfferService } from '../services/offer.service';
import { AuthService } from '../services/auth.service';
import { DonaucoinService } from '../services/donaucoin.service';
import { TradeData } from '../models/dswap.model';
import { User } from '../models/user.model';
import { Router } from '@angular/router';

declare var bootstrap: any;

// Einzelangebot - bei Tausch Abzug von angezeigtem DCoin
@Component({
  selector: 'app-single-offer',
  templateUrl: './single-offer.component.html',
  styleUrls: ['./single-offer.component.css']
})
export class SingleOfferComponent implements OnInit {
  mainImage: any;
  thumbnails: any[] = [];
  offerDetails: any;
  saldo: number = 0;

  constructor(
    private route: ActivatedRoute,
    private offerService: OfferService,
    private authService: AuthService,
    private donaucoinService: DonaucoinService,
    private router: Router
  ) { }

  ngOnInit(): void {
    const offerId = this.route.snapshot.paramMap.get('id');
    if (offerId) {
      this.loadOfferDetails(offerId);
      this.updateSaldo();

      // Listener für Benutzerstatusänderungen
      this.authService.currentUserObservable.subscribe(user => {
        this.updateSaldo(); // Aktualisieren Sie den Saldo, wenn sich der Benutzerstatus ändert
      });
    }
  }

  updateSaldo() {
    const currentUser: User | null = this.authService.getCurrentUser();
    if (currentUser && currentUser.id) {
      this.donaucoinService.getSaldo(currentUser.id).subscribe(
        saldo => {
          this.saldo = saldo;
        },
        error => {
          console.error('Fehler beim Abrufen des Saldos', error);
        }
      );
    }
  }
  goToDonaucoinPage() {
      this.router.navigate(['/donaucoin']); // Leitet den Benutzer zur Donaucoin-Seite um
  }
  loadOfferDetails(offerId: string): void {
    this.offerService.getOfferById(offerId).subscribe(
      response => {
        this.mainImage = response.mainImage;
        this.thumbnails = response.thumbnails;
        this.offerDetails = response;
      },
      error => {
        console.error('Error loading offer details:', error);
      }
    );
  }

  getSaldo() {
    const currentUser: User | null = this.authService.getCurrentUser();
    if (currentUser && currentUser.id) {
      this.donaucoinService.getSaldo(currentUser.id).subscribe(
        saldo => {
          this.saldo = saldo;
        },
        error => {
          console.error('Fehler beim Abrufen des Saldos', error);
        }
      );
    }
  }

  setMainImage(image: any): void {
    this.mainImage = image;
  }

  completeTrade() {
    const currentUser: User | null = this.authService.getCurrentUser();
    if (!currentUser || currentUser.id === undefined) {
      this.showModal("Bitte erst einloggen, oder registrieren");
      return;
    }

    const newSaldo = this.saldo - this.offerDetails.price_dcoin;
    if (newSaldo < 0) {
      this.showModal("Kaufe Donaucoin!");
      return;
    }

    // Donaucoin-Saldo im Backend aktualisieren
    this.donaucoinService.updateSaldo(currentUser.id, newSaldo).subscribe({
      next: () => {
        // Saldo im Frontend aktualisieren
        this.saldo = newSaldo;
        this.initiateTrade();
      },
      error: (error) => {
        console.error('Fehler beim Aktualisieren des Saldos', error);
        this.showModal("Ein Fehler ist aufgetreten. Bitte versuche es später erneut.");
      }
    });
  }

  initiateTrade() {
    const currentUser: User | null = this.authService.getCurrentUser();
    const buyerId = currentUser ? currentUser.id : null;

    if (buyerId && this.offerDetails) {
      const tradeData: TradeData = {
        price: this.offerDetails.price_dcoin,
        buyerId: buyerId,
        sellerId: this.offerDetails.sellerId,
        itemOrServiceId: this.offerDetails.itemOrServiceId,
        mainPictureId: this.offerDetails.mainImage.id,
        timeStamp: new Date()
      };

      this.offerService.createTrade(tradeData).subscribe(
        response => {
          console.log('Trade erfolgreich', response);
          this.showModal("Du hast den Tauschhandel erfolgreich abgeschlossen! Kontaktiere den Anbieter via e-mail");
        },
        error => {
          console.error('Fehler beim Tauschen', error);
        }
      );
    } else {
      console.error('Kein eingeloggter Benutzer gefunden oder offerDetails fehlen');
    }
  }

  showModal(message: string) {
    const modalElement = document.getElementById('tradeModal');
    if (modalElement) {
      const modalBody = modalElement.querySelector('.modal-body');
      if (modalBody) {
        modalBody.textContent = message;
      }

      const modal = new bootstrap.Modal(modalElement);
      modal.show();
    }
  }

  closeModal() {
    const modalElement = document.getElementById('tradeModal');
    if (modalElement) {
      const modal = bootstrap.Modal.getInstance(modalElement);
      if (modal) {
        modal.hide();
      }
    }
  }
}
