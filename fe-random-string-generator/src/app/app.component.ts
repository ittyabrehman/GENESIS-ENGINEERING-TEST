import {Component, OnInit} from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {NgIf} from "@angular/common";
import {RandomStringGeneratorService} from "./services/random-string-generator-service";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, ReactiveFormsModule, FormsModule, NgIf],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit {
  myForm: FormGroup | undefined;
  generatedString: string | undefined;

  constructor(private fb: FormBuilder, private stringGeneratorService: RandomStringGeneratorService,
              private http: HttpClient) {
  }

  ngOnInit() {
    // this.myForm = this.fb.group({
    //   targetSize: [Validators.required],
    //   length: [Validators.required],
    //   includeLowercase: [true],
    //   includeUppercase: [true],
    //   includeNumbers: [true],
    //   uniqueStrings: [true],
    // });

    this.myForm = new FormGroup({
      targetSize: new FormControl('', [Validators.required]),
      length: new FormControl('', [Validators.required]),
      includeLowercase: new FormControl(false, [Validators.required]),
      includeUppercase: new FormControl(false, [Validators.required]),
      includeNumbers: new FormControl(false, [Validators.required]),
      // uniqueStrings: new FormControl( false,[Validators.required]),
    })
  }

  async getStrings() {
    // if (this.myForm?.value) {
    //   this.stringGeneratorService.generateRandomString(this.myForm?.value)?.subscribe({
    //     next: (response) => {
    //       console.log(response)
    //     },
    //     error: (error) => {
    //       this.displayFormErrors()
    //     }
    //   })
    // }
    this.http.get("localhost:8080/api/v1/randomsStringGenerator/generate/findAll").subscribe(response =>{
      console.log(response)
    })

  }

  displayFormErrors() {
    if (this.myForm) {
      for (const control in this.myForm.controls) {
        if (this.myForm.controls[control].errors) {
          const errors = this.myForm.controls[control].errors;
          console.error(`Error in field '${control}':`, errors);
          this.generatedString = errors?.toString();
        }
      }
    }
  }

  restForm() {
    this.myForm?.reset()
  }
}
