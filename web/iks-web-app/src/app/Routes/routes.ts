import { Routes } from "@angular/router";
import {OrgControllerComponent} from "../components/org-list/org-controller.component";
import {AccountControllerComponent} from "../components/account/account-controller/account-controller.component";

// export let AccountRoutes : Routes = [
//   {path: '', component: AccountListComponent},
//   {path: 'add', component: AccountEditComponent}
//
// ];

export let AppRoutes : Routes = [
  {path: '', redirectTo: '/org', pathMatch:'full'},
  {path:  'org', component: OrgControllerComponent},
  {path: 'org/:orgId/accounts', component: AccountControllerComponent}//, children: AccountRoutes}
];


