import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ShoppingService {

  cartProductList = [];

  constructor() { }

  getProductList() {
    return this.cartProductList;
  }

  addProductToShoppingCart(product) {
    let productInCart = this.cartProductList.find(({id}) => id === product.id);
    if (!productInCart) {
      this.cartProductList.push({id:product.id, name:product.name, categoryName:product.categoryName, price:product.price, quantity:1});
      return;
    }
    productInCart.quantity += 1;
  }

  removeFromShoppingCart(product) {
    this.cartProductList = this.cartProductList.filter(({id}) => id !== product.id);
  }

  setProductQuantity(productId, quantity){
    let productInCart = this.cartProductList.find(({id}) => id === productId);
    if (productInCart) {
      productInCart.quantity = quantity;
    }

  }

  clearShoppingCart() {
    this.cartProductList = [];
  }

}
