import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-edit-product',
  templateUrl: './edit-product.component.html',
  styleUrls: ['./edit-product.component.css']
})
export class EditProductComponent implements OnInit {

  product;
  formdata;

  constructor(private route: ActivatedRoute, private http: HttpClient, private router: Router) {
  }

  ngOnInit(): void {
    let productId = Number(this.route.snapshot.paramMap.get('id'));
    const encodedCredential = "cristina:1234";
    let headers = new HttpHeaders();
    headers = headers.append("Authorization", "Basic " + btoa(encodedCredential));
    const _this = this;

    this.http.get("http://localhost:8080/products/" + productId, {headers: headers})
             .subscribe( (data) => { _this.product = data;
                                     console.log(data);
                                     _this.formdata = new FormGroup({ name: new FormControl(_this.product.name,
                                                                                            Validators.compose([ Validators.required ])),
                                                                     categoryName: new FormControl(_this.product.categoryName,
                                                                                                   Validators.compose([ Validators.required ])),
                                                                     imageUrl: new FormControl(_this.product.imageURL),
                                                                     price: new FormControl(_this.product.price,
                                                                                            Validators.compose([ Validators.required,
                                                                                                                 Validators.pattern("^[0-9]*$") ])),
                                                                     description: new FormControl(_this.product.description,
                                                                                                  Validators.compose([ Validators.required ]))
                                                                    });
                                   });
  }
  onClickSubmit(data) {
    this.product.name = data.name;
    this.product.categoryName = data.categoryName.categoryName;
    this.product.imageURL = data.imageUrl;
    this.product.price = data.price;
    this.product.description = data.description;

    let productId = Number(this.route.snapshot.paramMap.get('id'));
    const encodedCredential = "cristina:1234";
    let headers = new HttpHeaders();
    headers = headers.append("Authorization", "Basic " + btoa(encodedCredential));

    this.http.put("http://localhost:8080/products/" + this.product.id, this.product, { headers: headers })
             .subscribe((data) => { if (data) {
                                       window.alert("Product Saved!");
                                       this.router.navigate(['/product-list']);
                                    }
                                  },
                         err => { console.log(err);
                                  window.alert(err.error); }
                       );
  }

  onCancel(){
    this.router.navigate(['/product-list']);
  }

}
