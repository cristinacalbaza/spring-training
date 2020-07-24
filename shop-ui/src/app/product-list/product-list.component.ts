import { Component, OnInit } from '@angular/core';

import { products, productTableColumns } from './products';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {

  products = products;
  productTableColumns = productTableColumns;

  constructor() { }

  ngOnInit(): void {
  }

}
