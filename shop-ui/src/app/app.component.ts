import { Component } from '@angular/core';
import { DataService } from './data.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'shop-ui';

  constructor(private dataService: DataService){

  }

  ngOnInit(){
    this.dataService.getProductCategories();
  }
}
