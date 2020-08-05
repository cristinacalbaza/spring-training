import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { DataService } from '../data.service';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent implements OnInit {

    product;
    addProductData;
    Categories;

    Suppliers;

    constructor(private dataService: DataService, private route: ActivatedRoute, private http: HttpClient, private router: Router) {
      this.Categories = this.dataService.getCategories();
      this.Suppliers = this.dataService.getSuppliers();
    }

    ngOnInit(): void {
        this.product = {};
        this.addProductData = new FormGroup({ name: new FormControl("", Validators.compose([ Validators.required ])),
                                        categoryName: new FormControl("", Validators.compose([ Validators.required ])),
                                        supplierName: new FormControl("", Validators.compose([ Validators.required ])),
                                        imageUrl: new FormControl(),
                                        price: new FormControl("", Validators.compose([ Validators.required,
                                                               Validators.pattern("^[0-9]*$") ])),
                                        weight: new FormControl("", Validators.compose([ Validators.required ])),
                                        description: new FormControl("", Validators.compose([ Validators.required ]))
                                        });
    }

    onClickSubmit(data) {
      this.product.name = data.name;
      this.product.categoryId = data.categoryName.id;
      this.product.supplierId = data.supplierName.id;
      this.product.imageURL = data.imageUrl;
      this.product.price = data.price;
      this.product.description = data.description;
      this.product.weight = data.weight;

      let headers = this.dataService.getAuthHeader();

      this.http.post("http://localhost:8080/products/", this.product, { headers: headers })
               .subscribe((data) => { if (data) {
                                         window.alert("Product Saved!");
                                         this.router.navigate(['/product-list']);
                                      }
                                    },
                           err => window.alert(err.error) );
    }

    onCancel(){
      this.router.navigate(['/product-list']);
    }

}
