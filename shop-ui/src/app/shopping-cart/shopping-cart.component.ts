import { Component, OnInit, Input } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { shoppingCartTableColumns } from '../products';
import { ShoppingService } from '../shopping.service';

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit {

  cartProductList;
  shoppingCartTableColumns = shoppingCartTableColumns;

  constructor(private shoppingService: ShoppingService, private http: HttpClient, private router: Router) {
   this.updateCartProductList();
   }

  ngOnInit(): void {
  }

  removeFromShoppingCart(product) {
    this.shoppingService.removeFromShoppingCart(product);
    this.updateCartProductList();
  }

  updateCartProductList(){
    this.cartProductList = this.shoppingService.getProductList();
  }

  checkout() {
    const encodedCredential = "cristina:1234";
    let headers = new HttpHeaders();
    headers = headers.append("Authorization", "Basic " + btoa(encodedCredential));
    let options = { headers: headers };
    let products = [];
    this.cartProductList.forEach(function(product) {
         products.push( { productId: product.id, quantity: product.quantity });
    });
    let order = { createdAt: "",
                  address: { country: "RO", city: "Cluj-Napoca", county: "Cluj", streetAddress: "Meteor 82" },
                  products: products
                };

    this.http.post("http://localhost:8080/order", order, options)
        .subscribe((data) => { if (data) {
                                window.alert("Order created! Your products will be delivered as soon as possible!");
                               }
                             },
                    err => { window.alert(err.error); }
                  );
    this.router.navigate(['/product-list']);
    this.shoppingService.clearShoppingCart();
    this.updateCartProductList();
  }

}
