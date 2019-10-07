import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { OrgControllerComponent } from './components/org-list/org-controller.component';
import {FormsModule} from "@angular/forms";
import {RouterModule} from "@angular/router";
import {AppRoutes} from "./Routes/routes";
import {AppRoutingModule} from "./app-routing.module";
import {HttpClientModule} from "@angular/common/http";
import {RequestService} from "./services/request.service";
import { AccountControllerComponent } from './components/account/account-controller/account-controller.component';


const routes = AppRoutes;

@NgModule({
  declarations: [
    AppComponent,
    OrgControllerComponent,
    AccountControllerComponent,

  ],
  imports: [
    BrowserModule,
    FormsModule,
    RouterModule.forRoot(routes),
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [RequestService],
  bootstrap: [AppComponent]
})
export class AppModule { }
