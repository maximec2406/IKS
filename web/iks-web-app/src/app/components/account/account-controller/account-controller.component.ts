import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {RequestService} from "../../../services/request.service";
import {PluginService} from "../../../services/plugin.service";

@Component({
  selector: 'app-account-controller',
  templateUrl: './account-controller.component.html',
  styleUrls: ['./account-controller.component.css']

})
export class AccountControllerComponent implements OnInit {

  accList=[];
  orgId;
  accId;
  isAddAcc;
  accNumber;
  accBranchName;
  accBic;
  errorMsg;
  dateFrom;
  dateTo;
  plugin;

  constructor(private req: RequestService, private router: Router, private route: ActivatedRoute, private pluginService : PluginService) { }

  ngOnInit() {

    this.route.paramMap.subscribe(params => {
      this.orgId = params.get('orgId');
      this.req.getOrgAccounts(this.orgId).subscribe((data:any)=> this.accList = data) ;
    });
    this.isAddAcc = false;
    this.clearAccForm();
    this.errorMsg = '';
    this.dateFrom = null;
    this.dateTo = null;
  }


  getAddAccForm(){
    this.isAddAcc = true;
    this.accId = 0;
  }

  getAccList(){
    this.isAddAcc = false;
    this.clearAccForm();
  }

  clearAccForm(){
    this.accNumber="";
    this.accBranchName="";
    this.accBic="";
  }

  createAcc(){
    if (this.accId != 0){
      this.errorMsg = "Попытка обновления нового счета"
    } else {
      let acc = {
        "id": this.accId,
        "account": this.accNumber,
        "extBranchName": this.accBranchName,
        "bic": this.accBic,
        "orgId": this.orgId
      };
      this.req.createUpdateAcc(acc).subscribe((data: any) => {
        if (data) {
          this.req.getOrgAccounts(this.orgId).subscribe((data: any) => {
            this.accList = data;
            this.getAccList()
          });
        } else {
          this.errorMsg = 'Не удалось создать счет'
        }
      });
    }
  }

  editAccount(acc){
    this.getAddAccForm();
    this.accNumber = acc.account;
    this.accId = acc.id;
    this.accBic = acc.bic;
    this.accBranchName = acc.extBranchName;
  }

  deleteAcc(){
    if (this.accId == 0){
      this.errorMsg = "Попытка удаления несущестующего счета"
    } else {
      this.req.deleteAcc(this.accId).subscribe((data: any) => {
        if (data) {
          this.req.getOrgAccounts(this.orgId).subscribe((data: any) => {
            this.accList = data;
            this.getAccList()
          });
        } else {
          this.errorMsg = 'Не удалось удалить счет'
        }
      })
    }
  }

  updateAcc(){
    if (this.accId == 0){
      this.errorMsg = "Попытка сохранения несущестующего счета"
    } else {
      let acc = {"id": this.accId, "account" : this.accNumber, "extBranchName" : this.accBranchName, "bic" : this.accBic, "orgId": this.orgId};
      this.req.createUpdateAcc(acc).subscribe((data:any) => {
        if(data){
          this.req.getOrgAccounts(this.orgId).subscribe((data:any)=> {this.accList = data; this.getAccList()});
        } else { this.errorMsg = 'Не удалось сохранить счет'}
      });
    }
  }

  sendStateReq(){
     // this.plugin = this.pluginService.updatePlugin();
     // console.log(this.plugin);

    if (this.dateFrom == null || this.dateTo == null)
      this.errorMsg="Даты для запроса не заполнены";
    else {
      this.req.sendStmtReq(this.accId, this.dateFrom, this.dateTo).subscribe((data:any) => true);//alert("Юху")) ;
      //this.errorMsg="Запрос успешно отправлен";
    }
  }

  clearErrorMsg(){
    this.errorMsg="";
  }

}
