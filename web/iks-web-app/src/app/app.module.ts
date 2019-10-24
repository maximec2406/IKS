import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { OrgControllerComponent } from './components/org/org-controller.component';
import {FormsModule} from "@angular/forms";
import {RouterModule} from "@angular/router";
import {AppRoutes} from "./Routes/routes";
import {AppRoutingModule} from "./app-routing.module";
import {HttpClientModule} from "@angular/common/http";
import {RequestService} from "./services/request.service";
import { AccountControllerComponent } from './components/account/account-controller/account-controller.component';
import {PluginService} from "./services/plugin.service";
import { RpayorderControllerComponent } from './components/documents/rpayorder-controller/rpayorder-controller.component';
import { OrgHomeComponent } from './components/org/org-home/org-home.component';
import {FormBuilder} from "@angular/forms";


const routes = AppRoutes;

@NgModule({
  declarations: [
    AppComponent,
    OrgControllerComponent,
    AccountControllerComponent,
    RpayorderControllerComponent,
    OrgHomeComponent,

  ],
  imports: [
    BrowserModule,
    FormsModule,
    RouterModule.forRoot(routes),
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [
    RequestService,
    PluginService,
    FormBuilder
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
