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

}
