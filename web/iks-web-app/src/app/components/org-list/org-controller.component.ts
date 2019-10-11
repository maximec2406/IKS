import { Component, OnInit } from '@angular/core';
import {RequestService} from "../../services/request.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-org-controller',
  templateUrl: './org-controller.component.html',
  styleUrls: ['./org-controller.component.css']
})
export class OrgControllerComponent implements OnInit {

  orgList=[];
  isAddOrg;
  orgExtId;
  orgName;
  orgLegacyId;
  errorMsg;

  constructor(private req: RequestService, private router: Router) { }

  ngOnInit() {
    this.req.getOrgs().subscribe((data:any)=> this.orgList = data) ;
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
  }

  createOrg(){
    let org = {"extId" : this.orgExtId, "name" : this.orgName, "legacyId" : this.orgLegacyId}
    this.req.createOrg(org).subscribe((data:any) => {
      if(data){
        this.req.getOrgs().subscribe((data:any)=> {this.orgList = data; this.getOrgList()});
      } else { this.errorMsg = 'Не удалось сохранить организацию'}
    });
  }

  clearErrorMsg(){
    this.errorMsg='';
  }

  getOrgAccounts(id){
    this.router.navigate(['org/'+ id +'/accounts']);
  }





}
