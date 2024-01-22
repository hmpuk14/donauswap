import { Component, OnInit, ViewChild } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { DonaucoinService } from '../services/donaucoin.service';
import { Subscription } from 'rxjs';
import { NgForm } from '@angular/forms';

declare var bootstrap: any;

@Component({
  selector: 'app-donaucoin',
  templateUrl: './donaucoin.component.html',
  styleUrls: ['./donaucoin.component.css']
})
export class DonaucoinComponent implements OnInit {
  @ViewChild('purchaseForm') purchaseForm!: NgForm;
  saldo: number = 0;
  purchaseAmount: number | null = null;
  userSubscription: Subscription = new Subscription();

  constructor(private authService: AuthService, private donaucoinService: DonaucoinService) {}

  ngOnInit() {
    this.userSubscription = this.authService.currentUserObservable.subscribe(
      (user) => {
        if (user && user.id) { // Zusätzliche Überprüfung hier
          this.updateSaldo(user.id);
        }
      }
    );
  }

    ngOnDestroy() {
      if (this.userSubscription) {
        this.userSubscription.unsubscribe();
      }
    }

    goBack() {
        window.history.back();
      }

   purchaseDonaucoin() {
     if (this.purchaseForm.valid && this.purchaseAmount && this.purchaseAmount > 0) {
       this.userSubscription = this.authService.currentUserObservable.subscribe(
         (user) => {
           if (user && user.id !== undefined) { // Zusätzliche Überprüfung, ob user.id existiert
             const newSaldo = this.saldo + (this.purchaseAmount ?? 0);
             this.donaucoinService.updateSaldo(user.id, newSaldo).subscribe({
               next: () => {
                 if (user.id) { // Stellen Sie sicher, dass user.id existiert
                   this.updateSaldo(user.id);
                 }
                 this.openModal();
               },
               error: (error) => {
                 console.error('Fehler beim Aktualisieren des Saldos', error);
               }
             });
           }
         }
       );
     }
   }

updateSaldo(userId: number) {
  this.donaucoinService.getSaldo(userId).subscribe(
    saldo => {
      this.saldo = saldo;
    },
    error => {
      console.error('Fehler beim Abrufen des Saldos', error);
    }
  );
}
  openModal() {
    const modalElement = document.getElementById('purchaseModal');
    if (modalElement) {
      const modal = new bootstrap.Modal(modalElement);
      modal.show();
    }
  }
  closeModal() {
    const modalElement = document.getElementById('purchaseModal');
    if (modalElement) {
      const modal = bootstrap.Modal.getInstance(modalElement);
      if (modal) {
        modal.hide();
      }
    }
    this.resetForm(); // Formular zurücksetzen
  }
  resetForm() {
    if (this.purchaseForm) {
      this.purchaseForm.resetForm();
    }
  }
}
