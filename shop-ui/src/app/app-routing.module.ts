import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProductListComponent } from './product-list/product-list.component';
import { ProductDetailsComponent } from './product-details/product-details.component';
import { ShoppingCartComponent } from './shopping-cart/shopping-cart.component';
import { EditProductComponent } from './edit-product/edit-product.component';
import { AddProductComponent } from './add-product/add-product.component';
import { LoginComponent } from './login/login.component';
import { LoginGuard } from './login.guard';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'product-list', component: ProductListComponent, canActivate: [LoginGuard] },
  { path: 'shopping-cart', component: ShoppingCartComponent, canActivate: [LoginGuard] },
  { path: 'product-details/:id', component: ProductDetailsComponent, canActivate: [LoginGuard] },
  { path: 'edit-product/:id', component: EditProductComponent, canActivate: [LoginGuard] },
  { path: 'add-product', component: AddProductComponent, canActivate: [LoginGuard] },
  { path: '',   redirectTo: '/product-list', pathMatch: 'full' },
  { path: '**', component: ProductListComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
