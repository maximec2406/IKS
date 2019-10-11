import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';

@Injectable()
export class RequestService {

  //private serverUrl = 'http://correqtst00.abr.loc:8383/';
  private serverUrl = 'http://localhost:8282/IKS/api';
  constructor(private http: HttpClient){ };

  getHeaders(){
    return new HttpHeaders({});//{'Authorization':'Bearer ' + localStorage.getItem("tfs18")});
  }

  getOrgs(){
    return this.http.get(this.serverUrl + "/org/all", {headers: this.getHeaders()})
  }

  createOrg(org){
    return this.http.post(this.serverUrl + "/org", org,{headers:  this.getHeaders()})
  }

  getOrgAccounts(id){
    return id !== 0 ? this.http.get(this.serverUrl + "/org/" + id + "/accounts",{headers:  this.getHeaders()}) : this.http.get(this.serverUrl + "/accounts",{headers:  this.getHeaders()});
  }

  createUpdateAcc(acc){
    return this.http.post(this.serverUrl + "/account", acc,{headers:  this.getHeaders()});
  }

  deleteAcc(accId){
    return this.http.delete(this.serverUrl + "/account/" + accId, {headers: this.getHeaders()});
  }

  sendStmtReq(accId, dateFrom, dateTo){
    return this.http.post(this.serverUrl + "/account/stmtreq", {"id": accId, "dateFrom": dateFrom, "dateTo": dateTo},{headers:  this.getHeaders()})
  }

}