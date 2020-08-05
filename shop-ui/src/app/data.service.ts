import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  Categories;
  Suppliers;
  username;
  password;
  headers = new HttpHeaders();

  constructor(private http: HttpClient, private router: Router) {
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

   authenticate(username, password){
      const encodedCredential = username + ":" + password;
      this.headers = new HttpHeaders();
      this.headers = this.headers.append("Authorization", "Basic " + btoa(encodedCredential));

      this.http.get("http://localhost:8080/customer/" + username, { headers: this.headers })
                .subscribe((data) => { if (data) { this.router.navigate(['/product-list']); }
                                                    },
                                           err => { window.alert("Wrong username or password!");
                                                  });
   }

   getAuthHeader(){
      return this.headers;
   }
}
