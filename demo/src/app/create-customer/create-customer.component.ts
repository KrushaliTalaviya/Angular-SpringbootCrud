import { Component } from '@angular/core';
import { Customer } from '../customer';
import { CustomerService } from '../service/customer.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormControl, FormGroup, UntypedFormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-create-customer',
  templateUrl: './create-customer.component.html',
  styleUrls: ['./create-customer.component.css']
})

export class CreateCustomerComponent {

  emailRegex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;

  customer: Customer = new Customer();
  isSubmitted: boolean = false;
  id!: number;
  errorMsg!: String;
  maxDate: Date;
  minDate: Date;
  customerForm !: UntypedFormGroup;
  errorEmail !: String;
  errorMobileNo !: String;

  constructor(private customerService: CustomerService, private router: Router, private route: ActivatedRoute) {
    this.minDate = new Date(2000, 0, 1);
    this.maxDate = new Date();
  }

  ngOnInit() {
    this.id = this.route.snapshot.params['id'];
    if (this.id != null) {
      this.customerService.getCustomerById(this.id).subscribe(data => {
        this.setCustomer(data);
      });
    } else {
      this.setCustomer(this.customer);
    }
  }

  setCustomer( customer : Customer ) {
    this.customerForm = new FormGroup({
      id: new FormControl(customer?.id, []),
      firstName: new FormControl(customer?.firstName, [Validators.required]),
      lastName: new FormControl(customer?.lastName, [Validators.required]),
      email: new FormControl(customer?.email, [Validators.required, Validators.pattern(this.emailRegex)]),
      mobileNo: new FormControl(customer?.mobileNo, [Validators.required, Validators.minLength(10), Validators.maxLength(12)]),
      dateOfBirth: new FormControl(customer?.dateOfBirth, [Validators.required]),
      age: new FormControl(customer?.age, [Validators.required, Validators.min(18), Validators.max(100)]),
      gender: new FormControl(customer?.gender, [Validators.required]),
      address1: new FormControl(customer?.address1, [Validators.required]),
      address2: new FormControl(customer?.address2),
    })
  }

  getFirstName() {
    return this.customerForm.get('firstName');
   }

  saveCustomer() {
    this.isSubmitted = true;
    if (this.customerForm.valid) {
      this.customerService.createCustomer(this.customerForm.value).subscribe(data => {
        if (data && data.error) {
          this.errorEmail = data.email;
          this.errorMobileNo = data.mobileNo;
        } else {
          this.goToCustomeList();
        }
      });
    }
  }

  goToCustomeList() {
    this.router.navigate(['/customerList'])
  }

}