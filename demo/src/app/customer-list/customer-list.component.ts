import { Component, OnInit } from '@angular/core';
import { Customer } from '../customer';
import { CustomerService } from '../service/customer.service';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmationDialogComponent } from '../dialog/confirmation-dialog/confirmation-dialog.component';

@Component({
  selector: 'app-customer-list',
  templateUrl: './customer-list.component.html',
  styleUrls: ['./customer-list.component.css']
})

export class CustomerListComponent implements OnInit {

  customers: Customer[] = [];

  constructor(private customerService: CustomerService, private router: Router, private dialog: MatDialog) {
  }

  title !: String;

  ngOnInit(): void {
    this.title = "Customers List"
    this.getCustomers();
  }

  // Get a list of all customer
  private getCustomers() {
    this.customerService.getAllCustomers().subscribe(data => {
      this.customers = data;
    });
  }

  // Navigate the customer to update
  editCustomer(id: any) {
    this.router.navigate(['/updateCustomer', id]);
  }

  // Delete the customer
  deleteCustomer(id: any) {
    this.customerService.deleteCustomer(id).subscribe((data: any) => {
      this.getCustomers();
    })
  }

  // Open the delete confirmation pop-up
  openDialog(id: any) {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      data: {
        message: 'Do you want to delete this customer?'
      }
    });
    dialogRef.afterClosed().subscribe((confirmed: boolean) => {
      if (confirmed) {
        this.deleteCustomer(id);
      }
    });
  }

}