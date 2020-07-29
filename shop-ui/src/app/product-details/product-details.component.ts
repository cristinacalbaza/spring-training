import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {

  product;

  constructor(private route: ActivatedRoute, private http: HttpClient, private router: Router) {
  }

  ngOnInit(): void {
    let productId = Number(this.route.snapshot.paramMap.get('id'));
    const encodedCredential = "cristina:1234";
    let headers = new HttpHeaders();
    headers = headers.append("Authorization", "Basic " + btoa(encodedCredential));

    this.http.get("http://localhost:8080/products/" + productId, {headers: headers})
        .subscribe((data) => this.product = data);
  }

  deleteProduct() {
    const encodedCredential = "cristina:1234";
    let headers = new HttpHeaders();
    headers = headers.append("Authorization", "Basic " + btoa(encodedCredential));

    if (confirm('Are you sure you want to remove the "' + this.product.name + '" product?"')){
      this.http.delete("http://localhost:8080/products/" + this.product.id, {headers: headers})
               .subscribe((data) => { this.router.navigate(['/product-list']); },
                          response => { window.alert('Product cannot be deleted!'); });
    }
  }

}
