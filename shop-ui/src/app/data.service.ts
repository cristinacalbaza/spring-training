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

        await this.http.get("http://localhost:8080/productCategory/", { headers: this.headers })
                       .subscribe((data) => this.Categories = data );

        await this.http.get("http://localhost:8080/supplier/", { headers: this.headers })
                 .subscribe((data) => this.Suppliers = data );
   }

   getCategories(){
     return this.Categories;
   }

   getSuppliers(){
    return this.Suppliers;
   }

   async authenticate(username, password){
      const encodedCredential = username + ":" + password;
      console.log(username);
      this.headers = new HttpHeaders();
      this.headers = this.headers.append("Authorization", "Basic " + btoa(encodedCredential));
      console.log(encodedCredential);

      await this.http.get("http://localhost:8080/customer/" + username, { headers: this.headers })
                .subscribe((data) => { if (data) { this.router.navigate(['/product-list']);
                                                   this.username = username;
                                                   this.password = password;
                                                   this.getProductCategories(); }
                                                    },
                                           err => { console.log(err);
                                                    window.alert("Wrong username or password!");
                                                  });
   }

   getAuthHeader(){
      return this.headers;
   }

   isAdmin(){
      if (this.username === "admin")
        return true;
      return false;
   }
}
