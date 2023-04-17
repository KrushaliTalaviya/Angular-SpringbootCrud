import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateCustomerComponent } from './create-customer/create-customer.component';
import { CustomerListComponent } from './customer-list/customer-list.component';

const routes: Routes = [
  {
    path: 'addCustomer',
    component: CreateCustomerComponent
  },
  {
    path: 'customerList',
    component: CustomerListComponent
  },
  {
    path: 'updateCustomer/:id',
    component: CreateCustomerComponent
  },
  {
    path: '',
    component: CustomerListComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }