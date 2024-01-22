import { Component, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AuthService } from '../services/auth.service';
import { User } from '../models/user.model';

declare var bootstrap: any;

// Registrierung - Unterstützung zu html
@Component({
  selector: 'app-register-modal',
  templateUrl: './register-modal.component.html',
  styleUrls: ['./register-modal.component.css']
})
export class RegisterModalComponent {
  @ViewChild('registerForm') registerForm!: NgForm;
  user: User = {
    firstName: '',
    lastName: '',
    street: '',
    postalCode: '',
    city: '',
    email: '',
    password: '',
    dcoinSaldo: 0
  };
  registrationError: string = '';
  submitted = false; // Hinzugefügt

  constructor(private authService: AuthService) {}

  register() {
    this.submitted = true; // Setzen auf true, wenn der Registrierungsbutton geklickt wird
    if (this.registerForm.valid) {
      this.authService.register(this.user).subscribe(
        response => {
          console.log('Registrierung erfolgreich!', response);
          this.resetForm();
          this.closeModal();
        },
        error => {
          console.error('Fehler bei der Registrierung', error);
          this.registrationError = 'Ein Benutzer mit dieser E-Mail-Adresse ist bereits registriert. Bitte logg Dich ein, oder verwenden eine andere E-Mail-Adresse.';
        }
      );
    }
  }

  resetForm() {
    if (this.registerForm) {
      this.registerForm.resetForm();
      this.submitted = false; // Zurücksetzen, wenn das Formular zurückgesetzt wird
      this.registrationError = ''; // Fehlermeldung zurücksetzen
    }
  }

  closeModal() {
    const modalElement = document.getElementById('registerModal');
    if (modalElement) {
      const modal = bootstrap.Modal.getInstance(modalElement);
      if (modal) {
        modal.hide();
      }
    }
    this.removeModalBackdrop();
    this.resetForm(); // Zurücksetzen des Formulars beim Schließen des Modals
  }

  removeModalBackdrop() {
    const backdrop = document.querySelector('.modal-backdrop');
    if (backdrop) {
      backdrop.remove();
    }
  }
}
