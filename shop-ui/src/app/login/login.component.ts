import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor, HttpClient, HttpHeaders } from '@angular/common/http';
import { DataService } from '../data.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private dataService: DataService, private route: ActivatedRoute, private http: HttpClient) { }

  formdata;

  ngOnInit(): void {

    this.formdata = new FormGroup({ username: new FormControl("",  Validators.compose([ Validators.required ])),
                                    password: new FormControl("",  Validators.compose([ Validators.required ])) });

  }

  onClickSubmit(data) {
    this.dataService.authenticate(data.username, data.password);

  }

}
