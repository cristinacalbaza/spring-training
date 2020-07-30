import { Component, OnInit } from '@angular/core';

import { productTableColumns } from '../products';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {

  products;
  productTableColumns = productTableColumns;

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    const encodedCredential = "cristina:1234";
    let headers = new HttpHeaders();
    headers = headers.append("Authorization", "Basic " + btoa(encodedCredential));

    this.http.get("http://localhost:8080/products", {headers: headers})
    .subscribe((data) => this.products = data);
  }

}
