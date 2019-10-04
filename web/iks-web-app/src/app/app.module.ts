import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { OrgListComponent } from './components/org-list/org-list.component';
import {FormsModule} from "@angular/forms";
import {RouterModule} from "@angular/router";
import {AppRoutes} from "./Routes/routes";
import {AppRoutingModule} from "./app-routing.module";
import {HttpClientModule} from "@angular/common/http";
import {RequestService} from "./services/request.service";

const routes = AppRoutes;

@NgModule({
  declarations: [
    AppComponent,
    OrgListComponent
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
