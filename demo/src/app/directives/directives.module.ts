import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NumbersDirective } from './numbers.directive';



@NgModule({
  declarations: [
    NumbersDirective
  ],
  imports: [
    CommonModule
  ],
  exports: [
    NumbersDirective
  ]
})

export class DirectivesModule { }
