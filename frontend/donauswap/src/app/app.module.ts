import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module'; // Hier definierst du deine Routen
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { HomeComponent } from './home/home.component';
import { NavbarComponent } from './navbar/navbar.component';
import { RegisterModalComponent } from './register-modal/register-modal.component';
import { LoginModalComponent } from './login-modal/login-modal.component';
import { TradeComponent } from './trade/trade.component';
import { ReactiveFormsModule } from '@angular/forms';
import { DonaucoinComponent } from './donaucoin/donaucoin.component';
import { SingleOfferComponent } from './single-offer/single-offer.component';
import { CategoryOfferComponent } from './category-offer/category-offer.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NavbarComponent,
    RegisterModalComponent,
    LoginModalComponent,
    TradeComponent,
    DonaucoinComponent,
    SingleOfferComponent,
    CategoryOfferComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
