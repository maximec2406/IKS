import { Component, OnInit } from '@angular/core';
import {RequestService} from "../../services/request.service";
import {Router} from "@angular/router";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-org-controller',
  templateUrl: './org-controller.component.html',
  styleUrls: ['./org-controller.component.css']
})
export class OrgControllerComponent implements OnInit {

  orgList=null;
  isAddOrg;
  orgExtId;
  orgName;
  orgLegacyId;
  orgInn;
  errorMsg;

  constructor(private req: RequestService, private router: Router) { }

  ngOnInit() {
    this.getOrgs();
    this.isAddOrg = false;
    this.clearOrgForm();
    this.errorMsg = '';
  }

  getAddOrgForm(){
    this.isAddOrg = true;
  }
  getOrgList(){
    this.isAddOrg = false;
    this.clearOrgForm();
  }

  clearOrgForm(){
    this.orgName='';
    this.orgExtId='';
    this.orgLegacyId='';
    this.orgInn='';
  }

  createOrg(){
    let org = {"extId" : this.orgExtId, "name" : this.orgName, "legacyId" : this.orgLegacyId, "inn": this.orgInn}
    this.req.createOrg(org).subscribe((data:any) => {
      data ? this.getOrgs() : this.errorMsg = 'Не удалось сохранить организацию'
    });
  }

  clearErrorMsg(){
    this.errorMsg='';
  }

  getOrgHome(id){
    this.router.navigate(['org/'+ id]);
  }


  getOrgs(){
    this.req.getOrgs().then(
      (data:any)=> this.orgList = data,
      (data)=> {data instanceof HttpErrorResponse ? this.errorMsg = "ошибка запроса сервера" : this.orgList = []})//.subscribe((data:any)=> this.orgList = data) ;
  }

}
