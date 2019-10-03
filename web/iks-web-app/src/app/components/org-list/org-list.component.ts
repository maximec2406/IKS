import { Component, OnInit } from '@angular/core';
import {RequestService} from "../../services/request.service";

@Component({
  selector: 'app-org-list',
  templateUrl: './org-list.component.html',
  styleUrls: ['./org-list.component.css']
})
export class OrgListComponent implements OnInit {

  orgList;
  isAddOrg;
  orgExtId;
  orgName;
  orgLegacyId;

  constructor(private req: RequestService) { }

  ngOnInit() {
    this.orgList = this.req.getOrgs();
    this.isAddOrg = false;
    this.orgExtId="";
    this.orgName="";
    this.orgLegacyId="";
  }

  getAddLOrgForm(){
    this.isAddOrg = !this.isAddOrg;
  }

  createOrg(){

    let org = {"extId" : this.orgExtId, "name" : this.orgName, "legacyId" : this.orgLegacyId}
    this.req.createOrg(org).subscribe((data:any) => {data ? this.getAddLOrgForm() : alert(data)});

  }

}
