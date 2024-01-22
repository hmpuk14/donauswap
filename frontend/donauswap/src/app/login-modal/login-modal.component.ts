 import { Component, ViewChild, OnInit } from '@angular/core'; // Importieren von OnInit
 import { NgForm } from '@angular/forms';
 import { AuthService } from '../services/auth.service';

 declare var bootstrap: any;

 @Component({
   selector: 'app-login-modal',
   templateUrl: './login-modal.component.html',
   styleUrls: ['./login-modal.component.css']
 })
 export class LoginModalComponent implements OnInit {
   @ViewChild('loginForm') loginForm!: NgForm;
   email: string = '';
   password: string = '';
   loginError: string = '';
   submitted: boolean = false;

   constructor(private authService: AuthService) {}

   ngOnInit() { // OnInit-Methode
     const modalElement = document.getElementById('loginModal');
     if (modalElement) {
       modalElement.addEventListener('shown.bs.modal', () => {
         this.resetForm();
       });
       modalElement.addEventListener('hidden.bs.modal', () => {
         this.resetForm();
       });
     }
   }
  onLogin() {
    this.submitted = true;
    this.loginError = '';

    if (this.email && this.password) {
      this.authService.login(this.email, this.password).subscribe(
        response => {
          if (response) {
            console.log('Login erfolgreich', response);
            this.resetForm();
            this.closeModal();
          } else {
            this.loginError = 'Überprüfe die korrekte Eingabe von E-Mail und Passwort. Bist du schon registriert?';
          }
        },
        error => {
          console.error('Login fehlgeschlagen', error);
          this.loginError = 'Ein Fehler ist aufgetreten. Bitte versuche es später erneut.';
        }
      );
    }
  }

 resetForm() {
   this.email = '';
   this.password = '';
   this.submitted = false;
   this.loginError = '';
   if (this.loginForm) {
     this.loginForm.resetForm();

      // Explizites Zurücksetzen der Formularsteuerungen
      Object.keys(this.loginForm.controls).forEach(key => {
        const control = this.loginForm.controls[key];
        control.markAsPristine();
        control.markAsUntouched();
        control.updateValueAndValidity();
      });
    }
  }

  openModal() {
    this.resetForm();
  }

  closeModal() {
    const modalElement = document.getElementById('loginModal');
    if (modalElement) {
      const modal = bootstrap.Modal.getInstance(modalElement);
      if (modal) {
        modal.hide();
      }
    }
    this.resetForm();
  }
}
