import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ShoppingService } from '../shopping.service';
import { DataService } from '../data.service';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {

  @Input() cartProductList;
  product;
  @Output() productAdded = new EventEmitter();

  constructor(private dataService: DataService, private route: ActivatedRoute, private http: HttpClient, private router: Router, private shoppingService: ShoppingService) {
  }

  ngOnInit(): void {
    let productId = Number(this.route.snapshot.paramMap.get('id'));
    let headers = this.dataService.getAuthHeader();

    this.http.get("http://localhost:8080/products/" + productId, {headers: headers})
        .subscribe((data) => this.product = data);
  }

  deleteProduct() {
    let headers = this.dataService.getAuthHeader();

    if (confirm('Are you sure you want to remove the "' + this.product.name + '" product?"')){
      this.http.delete("http://localhost:8080/products/" + this.product.id, {headers: headers})
               .subscribe((data) => { this.router.navigate(['/product-list']); },
                          response => { window.alert('Product cannot be deleted!'); });
    }
  }

  addToShoppingCart(){
    this.shoppingService.addProductToShoppingCart(this.product);
    window.alert("Product added to Cart!");

  }

}
