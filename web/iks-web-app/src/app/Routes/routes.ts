import { Routes } from "@angular/router";
import {OrgControllerComponent} from "../components/org/org-controller.component";
import {AccountControllerComponent} from "../components/account/account-controller/account-controller.component";
import {OrgHomeComponent} from "../components/org/org-home/org-home.component";
import {RpayorderControllerComponent} from "../components/documents/rpayorder-controller/rpayorder-controller.component";
import {LoginComponent} from "../components/login/login.component";
// import {PluginonloadGuard} from "../Guards/pluginonload.guard"

// export let AccountRoutes : Routes = [
//   {path: '', component: AccountListComponent},
//   {path: 'add', component: AccountEditComponent}
//
// ];

export let AppRoutes : Routes = [
  {path: 'login', component: LoginComponent},
  {path: '', redirectTo: '/org', pathMatch:'full'},
  {path:  'org', component: OrgControllerComponent},
  {path: 'org/:orgId', component: OrgHomeComponent},
  {path: 'org/:orgId/accounts', component: AccountControllerComponent},// children: AccountRoutes}
  {path: 'org/:orgId/rpayorder', component: RpayorderControllerComponent}
];


