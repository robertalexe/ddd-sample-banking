import { FormGroup } from '@angular/forms';
import {
  Component,
  OnInit,
  ViewChild,
  ElementRef,
  Input,
  Output,
  EventEmitter
} from '@angular/core';

import { StoreService } from '../../../shared/store/store.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-modal-container',
  templateUrl: './modal-container.component.html',
  styleUrls: ['./modal-container.component.scss']
})
export class ModalContainerComponent implements OnInit {
  @Input()
  title: string;
  @Output()
  submit: EventEmitter<void> = new EventEmitter<void>();
  @Output()
  closed: EventEmitter<void> = new EventEmitter<void>();
  @ViewChild('modal')
  modal: ElementRef;

  disableButton: boolean = true;
  actionForm: FormGroup;
  transactionSubscription: Subscription;

  constructor(private store: StoreService) {}

  ngOnInit() {
    this.transactionSubscription = this.store
      .getTransactionState()
      .subscribe((actionForm: FormGroup) => {
        this.actionForm = actionForm;
        this.disableButton = actionForm.invalid;
      });
  }

  open(title?: string) {
    this.title = title;
    this.modal.nativeElement.classList.add('show');
    this.modal.nativeElement.style.display = 'block';
  }

  close() {
    this.modal.nativeElement.classList.remove('show');
    this.modal.nativeElement.style.display = 'none';
    // this.transactionSubscription.unsubscribe();
    this.resetAction();
    this.closed.emit();
  }

  onSubmit() {
    this.submit.emit();
    this.resetAction();
  }

  private resetAction() {
    if (this.actionForm) {
      this.actionForm.reset();
      this.store.updateTransactionState(this.actionForm);
    }
  }
}
