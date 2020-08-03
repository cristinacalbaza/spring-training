import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  Categories;
  Suppliers;

  constructor(private http: HttpClient) {
  }

  async getProductCategories(){
        const encodedCredential = "cristina:1234";
        let headers = new HttpHeaders();
        headers = headers.append("Authorization", "Basic " + btoa(encodedCredential));

        await this.http.get("http://localhost:8080/productCategory/", { headers: headers })
                       .subscribe((data) => this.Categories = data );

        await this.http.get("http://localhost:8080/supplier/", { headers: headers })
                 .subscribe((data) => this.Suppliers = data );
   }

   getCategories(){
     return this.Categories;
   }

   getSuppliers(){
    return this.Suppliers;
   }
}
