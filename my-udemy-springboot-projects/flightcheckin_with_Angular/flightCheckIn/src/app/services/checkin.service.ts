//first 


import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CheckinService {

  reservationUrl = "http://localhost:9091/flightservices/reservations/"
  reservationData:any ;

  constructor(private _httpClient: HttpClient) {}

  //to fetch the reservation from db
    public getReservation(id:Number):any{
       return  this._httpClient.get(this.reservationUrl+id);
    }

  //to update the reservation in db
    public checkIn(checkInRequest:any){
      return this._httpClient.put(this.reservationUrl, checkInRequest);
    }
   
}
