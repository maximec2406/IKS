import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { OrgControllerComponent } from './components/org/org-controller.component';
import {FormGroup, FormsModule, ReactiveFormsModule} from "@angular/forms";
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
import {CheckrpayorderService} from "./services/checks/checkrpayorder.service";
import {MaterializeModule} from "angular2-materialize";


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
    HttpClientModule,
    ReactiveFormsModule,
    MaterializeModule
  ],
  providers: [
    RequestService,
    PluginService,
    FormBuilder,
    CheckrpayorderService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
