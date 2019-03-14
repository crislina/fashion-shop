import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { EshopComponent } from './eshop/eshop.component';
import { ProductsComponent } from './eshop/products/products.component';
import { OrdersComponent } from './eshop/orders/orders.component';
import { CartComponent } from './eshop/cart/cart.component';

@NgModule({
  declarations: [
    AppComponent,
    EshopComponent,
    ProductsComponent,
    OrdersComponent,
    CartComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
