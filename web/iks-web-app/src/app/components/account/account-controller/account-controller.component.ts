import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {RequestService} from "../../../services/request.service";

@Component({
  selector: 'app-account-controller',
  templateUrl: './account-controller.component.html',
  styleUrls: ['./account-controller.component.css']
})
export class AccountControllerComponent implements OnInit {

  accList=[];
  orgId;
  isAddAcc;
  accExtId;
  accNumber;
  accBranchName;
  accBic;
  errorMsg;

  constructor(private req: RequestService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit() {

    this.route.paramMap.subscribe(params => {
      this.orgId = params.get('orgId');
      this.req.getOrgAccounts(this.orgId).subscribe((data:any)=> this.accList = data) ;
    });
    this.isAddAcc = false;
    this.clearAccForm();
    this.errorMsg = '';

  }

  getAddAccForm(){
    this.isAddAcc = true;
  }

  getAccList(){
    this.isAddAcc = false;
    this.clearAccForm();
  }

  clearAccForm(){
    this.accExtId="";
    this.accNumber="";
    this.accBranchName="";
    this.accBic="";
  }

  createAcc(){
    let acc = {"extid" : this.accExtId, "account" : this.accNumber, "extBranchName" : this.accBranchName, "bic" : this.accBic, "orgId": this.orgId};
    this.req.createAcc(acc).subscribe((data:any) => {
      if(data){
        this.req.getOrgAccounts(this.orgId).subscribe((data:any)=> {this.accList = data; this.getAccList()});
      } else { this.errorMsg = 'Не удалось сохранить счет'}
    });
  }



}
