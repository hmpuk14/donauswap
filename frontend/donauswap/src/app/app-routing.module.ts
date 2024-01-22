import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { TradeComponent } from './trade/trade.component';
import { DonaucoinComponent } from './donaucoin/donaucoin.component';
import { SingleOfferComponent } from './single-offer/single-offer.component';
import { CategoryOfferComponent } from './category-offer/category-offer.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'handeln', component: TradeComponent },
  { path: 'donaucoin', component: DonaucoinComponent },
  { path: 'kategorie/:id', component: CategoryOfferComponent },
  { path: 'tauschen/:id', component: SingleOfferComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
