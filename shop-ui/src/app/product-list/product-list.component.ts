import { Component, OnInit } from '@angular/core';

import { productTableColumns } from '../products';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { DataService } from '../data.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {

  products;
  productTableColumns = productTableColumns;

  constructor(private dataService: DataService, private http: HttpClient) { }

  ngOnInit(): void {
    let headers = this.dataService.getAuthHeader();

    this.http.get("http://localhost:8080/products", {headers: headers})
    .subscribe((data) => this.products = data);
  }

}
