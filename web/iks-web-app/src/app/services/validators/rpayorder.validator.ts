import { AbstractControl } from '@angular/forms';
import {RequestService} from "../request.service";
import {ActivatedRoute} from "@angular/router";

export class rPayOrderValidators {

  private static req: RequestService;
  private static route: ActivatedRoute;

  static validDocNumber(field: AbstractControl){

    if (!field.value){
      return null;
    } else {
      return true;
    }

  }
}
