import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProductListComponent } from './product-list/product-list.component';
import { ProductDetailsComponent } from './product-details/product-details.component';
import { ShoppingCartComponent } from './shopping-cart/shopping-cart.component';
import { EditProductComponent } from './edit-product/edit-product.component';
import { AddProductComponent } from './add-product/add-product.component';

const routes: Routes = [
  { path: 'product-list', component: ProductListComponent },
  { path: 'shopping-cart', component: ShoppingCartComponent },
  { path: 'product-details/:id', component: ProductDetailsComponent },
  { path: 'edit-product/:id', component: EditProductComponent },
  { path: 'add-product', component: AddProductComponent },
  { path: '',   redirectTo: '/product-list', pathMatch: 'full' },
  { path: '**', component: ProductListComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
