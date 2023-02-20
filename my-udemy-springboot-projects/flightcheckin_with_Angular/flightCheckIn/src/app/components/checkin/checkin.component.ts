import { Component } from '@angular/core';
import { CheckinService } from 'src/app/services/checkin.service';
import {Router} from '@angular/router'

@Component({
  selector: 'app-checkin',
  templateUrl: './checkin.component.html',
  styleUrls: ['./checkin.component.css']
})
export class CheckinComponent {

  noOfBags:Number;
  data:any;

  constructor(private service:CheckinService, private router:Router) {
  }

  //to use data object in checkin.compğonent.html page
  ngOnInit() : void  {
    this.data = this.service.reservationData;
  }

  public checkIn()  {
    let request = {
      "id":this.data.id,
      "checkIn": true,
      "numberOfBags": this.noOfBags
    }

    this.service.checkIn(request).subscribe(res=>{
      this.router.navigate(['/confirm']);
    })
  }
}
