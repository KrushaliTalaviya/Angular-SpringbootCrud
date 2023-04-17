import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Customer } from '../customer';
import { HttpClient, HttpClientModule, HttpParams } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})

export class CustomerService {

  open(content: any, arg1: { ariaLabelledBy: string; }) {
    throw new Error('Method not implemented.');
  }

  constructor(private httpClient: HttpClient) { }

  private baseURL = "http://localhost:8080/customer/";

  getAllCustomers(): Observable<Customer[]> {
    return this.httpClient.get<Customer[]>(`${this.baseURL}`);
  }

  createCustomer(customer: Customer): Observable<any> {
    return this.httpClient.post(`${this.baseURL}addNewCustomer`, customer);
  }

  deleteCustomer(id: number): Observable<Object> {
    return this.httpClient.delete(`${this.baseURL}deleteCustomer/${id}`)
  }

  getCustomerById(id: number): Observable<Customer> {
    return this.httpClient.get<Customer>(`${this.baseURL}updateCustomer/${id}`)
  }

}